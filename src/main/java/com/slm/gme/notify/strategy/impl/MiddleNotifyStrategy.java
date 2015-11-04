package com.slm.gme.notify.strategy.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.slm.gme.common.CollectionUtil;
import com.slm.gme.common.CommonUtil;
import com.slm.gme.common.DBCache;
import com.slm.gme.framework.PojoDomain;
import com.slm.gme.notify.common.NotifyConstant;
import com.slm.gme.notify.dao.NotifyDao;
import com.slm.gme.notify.domain.GmeHttpConfig;
import com.slm.gme.notify.domain.HttpBaseResult;
import com.slm.gme.notify.domain.ModuleCompt;
import com.slm.gme.notify.domain.NotifyConfig;
import com.slm.gme.notify.domain.NotifyTask;
import com.slm.gme.notify.strategy.NotifyStrategy;
import com.slm.gme.notify.util.GmeHttpProcesser;
import com.slm.gme.notify.util.NotifyUtil;

/**
 * 通知发送策略：直接发送，如果失败则入库由定时任务发送，如果成功则不入库.
 * @author daemon
 *
 */
public class MiddleNotifyStrategy implements NotifyStrategy
{
    @Autowired
    private NotifyDao notifyDao;
    
    /**
     * 基于Ghttp协议的普通消息协议传输xml消息体
     * @param msgBody
     * @param operType
     * @param notifyObj
     * @param notifyObjTypeCode
     * @param moduleCompt
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean notify(String msgBody, String operType, PojoDomain notifyObj, String notifyObjTypeCode,
        ModuleCompt moduleCompt)
    {
        GmeHttpConfig config = NotifyUtil.getGmeHttpConfig();
        String srcComptCode = config.getSourceComptCode();
        String destComptCode = moduleCompt.getDestComptCode();
        NotifyConfig notifyConfig = DBCache.getNotifyConfigByCode(destComptCode);
        
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("version", config.getVersion());
        headers.put("traceId", NotifyUtil.genralMessageId());
        headers.put("authSecret", notifyConfig.getSecurityKey());
        //TODO 通知任务表中增加接口类型
        headers.put("interfaceType", NotifyUtil.genralInterfaceType(srcComptCode, destComptCode));
        headers.put("timeStamp", CommonUtil.formatDate(new Date()));
        headers.put("sourceAreaCode", config.getSourceAreaCode());
        headers.put("sourceComptCode", config.getSourceComptCode());
        headers.put("sourceComptType", String.valueOf(config.getSourceComptType()));
        headers.put("destAreaCode", notifyConfig.getAreaCode());
        headers.put("destComptType", String.valueOf(notifyConfig.getComptType()));
        headers.put("destComptCode", String.valueOf(notifyConfig.getComptCode()));
        headers.put("serviceType", notifyObjTypeCode);
        
        HttpBaseResult result = GmeHttpProcesser.send(notifyConfig.getNotifyUrl(), headers, msgBody);
        
        //发送消息失败入库
        if (0 != result.getResponseCode() && 200 != result.getResponseCode())
        {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("interfaceType", NotifyUtil.genralInterfaceType(srcComptCode, destComptCode));
            params.put("actionType", NotifyUtil.transOpertype(operType));
            params.put("destComptType", notifyConfig.getComptType());
            params.put("destComptCode", notifyConfig.getComptCode());
            params.put("type", Integer.parseInt(notifyObjTypeCode));
            params.put("objectId", notifyObj.getId());
            List<NotifyTask> taskList = notifyDao.queryListByMultiCondition(params, NotifyTask.class);
            //如果原先已经有任务记录，先删除任务记录
            NotifyTask notifyTask = null;
            if (!CollectionUtil.isEmpty(taskList))
            {
                notifyTask = taskList.get(0);
                //更新失败次数+1
                //notifyService.update(notifyTask);
            }
            else
            {
                notifyTask = new NotifyTask();
                notifyTask.setActionType(NotifyUtil.transOpertype(operType));
                notifyTask.setDestAreaCode(notifyConfig.getAreaCode());
                notifyTask.setDestComptCode(notifyConfig.getComptCode());
                notifyTask.setDestComptType(notifyConfig.getComptType());
                notifyTask.setFailCount(0);
                notifyTask.setInterfaceType(NotifyUtil.genralInterfaceType(srcComptCode, destComptCode));
                notifyTask.setLastUpdateDate(new Date());
                notifyTask.setObjectId(notifyObj.getId());
                notifyTask.setType(Integer.parseInt(notifyObjTypeCode));
                notifyTask.setCreateDate(new Date());
                notifyTask.setStatus(NotifyConstant.NotifyStatus.INIT_STATUS);
                notifyTask.setMessageId(NotifyUtil.genralMessageId());
                notifyDao.saveOrUpdate(notifyTask);
            }
            
            return false;
        }
        return true;
    }
}
