package com.slm.gme.notify.strategy.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.slm.gme.common.CommonUtil;
import com.slm.gme.common.DBCache;
import com.slm.gme.framework.PojoDomain;
import com.slm.gme.notify.domain.GmeHttpConfig;
import com.slm.gme.notify.domain.HttpBaseResult;
import com.slm.gme.notify.domain.ModuleCompt;
import com.slm.gme.notify.domain.NotifyConfig;
import com.slm.gme.notify.strategy.NotifyStrategy;
import com.slm.gme.notify.util.GmeHttpProcesser;
import com.slm.gme.notify.util.NotifyUtil;

/**
 * 通知发送策略：直接发送，不入库
 * @author daemon
 *
 */
public class DirectNotifyStrategy implements NotifyStrategy
{
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
        String  destComptCode = moduleCompt.getDestComptCode();
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
        
        //网络异常
        if (500 == result.getResponseCode())
        {
            return false;
        }
        
        if (0 == result.getResponseCode() || 200 == result.getResponseCode())
        {
            return true;
        }
        
        return false;
    }
}
