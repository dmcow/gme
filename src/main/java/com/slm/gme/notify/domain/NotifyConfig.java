package com.slm.gme.notify.domain;

import com.slm.gme.common.CommonUtil;
import com.slm.gme.framework.PojoDomain;

public class NotifyConfig extends PojoDomain
{
    private static final long serialVersionUID = 1L;

    public String getAreaCode()
    {
        return CommonUtil.trim(this.getProperty("areaCode"));
    }

    public void setAreaCode(String areaCode)
    {
        this.addProperty("areaCode", areaCode);
    }

    public int getComptType()
    {
        return (Integer)this.getProperty("comptType");
    }

    public void setComptType(int comptType)
    {
        this.addProperty("comptType", comptType);
    }

    public String getComptCode()
    {
        return CommonUtil.trim(this.getProperty("comptCode"));
    }

    public void setComptCode(String comptCode)
    {
        this.addProperty("comptCode", comptCode);
    }

    public String getNotifyUrl()
    {
        return CommonUtil.trim(this.getProperty("notifyUrl"));
    }

    public void setNotifyUrl(String notifyUrl)
    {
        this.addProperty("notifyUrl", notifyUrl);
    }

    public int getProtocolType()
    {
        return (Integer)this.getProperty("protocolType");
    }

    public void setProtocolType(int protocolType)
    {
        this.addProperty("protocolType", protocolType);
    }

    public String getSecurityKey()
    {
        return CommonUtil.trim(this.getProperty("securityKey"));
    }

    public void setSecurityKey(String securityKey)
    {
        this.addProperty("securityKey", securityKey);
    }

    public String getNote()
    {
        return CommonUtil.trim(this.getProperty("note"));
    }

    public void setNote(String note)
    {
        this.addProperty("note", note);
    }    
}
