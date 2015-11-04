package com.slm.gme.notify.strategy;


import com.slm.gme.framework.PojoDomain;
import com.slm.gme.notify.domain.ModuleCompt;


/**
 * 通知消息的策略
 * @author zhangziwen
 *
 */
public interface NotifyStrategy
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
        ModuleCompt moduleCompt);
}
