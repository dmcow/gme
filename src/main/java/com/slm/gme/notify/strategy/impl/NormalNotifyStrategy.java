package com.slm.gme.notify.strategy.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.slm.gme.common.CollectionUtil;
import com.slm.gme.common.DBCache;
import com.slm.gme.framework.PojoDomain;
import com.slm.gme.notify.common.NotifyConstant;
import com.slm.gme.notify.dao.NotifyDao;
import com.slm.gme.notify.domain.GmeHttpConfig;
import com.slm.gme.notify.domain.ModuleCompt;
import com.slm.gme.notify.domain.NotifyConfig;
import com.slm.gme.notify.domain.NotifyTask;
import com.slm.gme.notify.strategy.NotifyStrategy;
import com.slm.gme.notify.util.NotifyUtil;

/**
 * 通知发送策略：直接入库，定时任务发送
 * @author daemon
 *
 */
public class NormalNotifyStrategy implements NotifyStrategy
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
        GmeHttpConfig httpConfig = NotifyUtil.getGmeHttpConfig();
        String srcComptCode = httpConfig.getSourceComptCode();
        String destComptCode = moduleCompt.getDestComptCode();
        NotifyConfig config = DBCache.getNotifyConfigByCode(destComptCode);
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("interfaceType", NotifyUtil.genralInterfaceType(srcComptCode, destComptCode));
        params.put("actionType", NotifyUtil.transOpertype(operType));
        params.put("destComptType", config.getComptType());
        params.put("destComptCode", config.getComptCode());
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
            notifyTask.setCreateDate(new Date());
            notifyTask.setDestAreaCode(config.getAreaCode());
            notifyTask.setDestComptCode(destComptCode);
            notifyTask.setDestComptType(config.getComptType());
            notifyTask.setFailCount(0);
            notifyTask.setInterfaceType(NotifyUtil.genralInterfaceType(srcComptCode, destComptCode));
            notifyTask.setLastUpdateDate(new Date());
            notifyTask.setMessageId(NotifyUtil.genralMessageId());
            notifyTask.setObjectId(notifyObj.getId());
            //notifyTask.setServiceId("1000");
            notifyTask.setStatus(NotifyConstant.NotifyStatus.INIT_STATUS);
            notifyTask.setType(Integer.parseInt(notifyObjTypeCode));
            //NotifyService notifyService = (NotifyService)ServiceFactory.getBean("notifyService");
            notifyDao.saveOrUpdate(notifyTask);
        }
        
        return true;
    }
}
