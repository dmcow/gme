package com.slm.gme.notify.domain;

import java.util.Date;

import com.slm.gme.common.CommonUtil;
import com.slm.gme.framework.PojoDomain;


/**
 * 通知任务
 * @author zhangziwen
 *
 */
public class NotifyTask extends PojoDomain
{
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = 1L;

    public String getMessageId()
    {
        return CommonUtil.trim(getProperty("messageId"));
    }

    public void setMessageId(String messageId)
    {
        addProperty("messageId", messageId);
    }

    public int getActionType()
    {
        return (Integer)getProperty("actionType");
    }

    public void setActionType(int actionType)
    {
        addProperty("actionType", actionType);
    }

    public String getDestAreaCode()
    {
        return CommonUtil.trim(getProperty("destAreaCode"));
    }

    public void setDestAreaCode(String destAreaCode)
    {
        addProperty("destAreaCode", destAreaCode);
    }

    public int getDestComptType()
    {
        return (Integer)getProperty("destComptType");
    }

    public void setDestComptType(int destComptType)
    {
        addProperty("destComptType", destComptType);
    }

    public String getDestComptCode()
    {
        return CommonUtil.trim(getProperty("destComptCode"));
    }

    public void setDestComptCode(String destComptCode)
    {
        addProperty("destComptCode", destComptCode);
    }

    public int getObjectId()
    {
        return (Integer)getProperty("objectId");
    }

    public void setObjectId(int objectId)
    {
        addProperty("objectId", objectId);
    }

    public String getServiceId()
    {
        return CommonUtil.trim(getProperty("serviceId"));
    }

    public void setServiceId(String serviceId)
    {
        addProperty("serviceId", serviceId);
    }

    public int getType()
    {
        return (Integer)getProperty("type");
    }

    public void setType(int type)
    {
        addProperty("type", type);
    }

    public int getStatus()
    {
        return (Integer)getProperty("status");
    }

    public void setStatus(int status)
    {
        addProperty("status", status);
    }

    public Date getCreateDate()
    {
        return (Date)getProperty("createDate");
    }

    public void setCreateDate(Date createDate)
    {
        addProperty("createDate", createDate);
    }

    public Date getLastUpdateDate()
    {
        return (Date)getProperty("lastUpdateDate");
    }

    public void setLastUpdateDate(Date lastUpdateDate)
    {
        addProperty("lastUpdateDate", lastUpdateDate);
    }

    public int getFailCount()
    {
        return (Integer)getProperty("failCount");
    }

    public void setFailCount(int failCount)
    {
        addProperty("failCount", failCount);
    }

    public String getInterfaceType()
    {
        return CommonUtil.trim(getProperty("interfaceType"));
    }
    
    public void setInterfaceType(String interfaceType)
    {
        addProperty("interfaceType", interfaceType);
    }
}
