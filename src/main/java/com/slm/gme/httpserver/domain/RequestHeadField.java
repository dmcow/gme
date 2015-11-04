package com.slm.gme.httpserver.domain;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RequestHeadField
{
    private Map<String, Object> hearderMap = new HashMap<String, Object>();
    
    public void addHeader(String headerName, Object headerValue)
    {
        this.hearderMap.put(headerName, headerValue);
    }
    
    public Object getHeader(String HeaderName)
    {
        return null == this.hearderMap.get(HeaderName) ? null : this.hearderMap.get(HeaderName);
    }
    
    public String getVersion()
    {
        return null == this.hearderMap.get("version") ? null : String.valueOf(this.hearderMap.get("version"));
    }
    
    public String getTraceId()
    {
        return null == this.hearderMap.get("traceid") ? null : String.valueOf(this.hearderMap.get("traceid"));
    }
    
    public String getInterfaceType()
    {
        return null == this.hearderMap.get("interfacetype") ? null
            : String.valueOf(this.hearderMap.get("interfacetype"));
    }
    
    public Integer getSourceComptType()
    {
        return null == this.hearderMap.get("sourcecompttype") ? null
            : Integer.valueOf(String.valueOf(this.hearderMap.get("sourcecompttype")));
    }
    
    public String getSourceAreaCode()
    {
        return null == this.hearderMap.get("sourceareacode") ? null
            : String.valueOf(this.hearderMap.get("sourceareacode"));
        
    }
    
    public String getSourceComptCode()
    {
        return null == this.hearderMap.get("sourcecomptcode") ? null
            : String.valueOf(this.hearderMap.get("sourcecomptcode"));
    }
    
    public String getDestComptType()
    {
        return null == this.hearderMap.get("destcompttype") ? null
            : String.valueOf(this.hearderMap.get("destcompttype"));
    }
    
    public String getDestAreaCode()
    {
        return null == this.hearderMap.get("destareacode") ? null : String.valueOf(this.hearderMap.get("destareacode"));
    }
    
    public Integer getDestComptCode()
    {
        return null == this.hearderMap.get("destcomptcode") ? null
            : Integer.valueOf((String)(this.hearderMap.get("destcomptcode")));
    }
    
    public String getAuthSecret()
    {
        return null == this.hearderMap.get("authsecret") ? null : String.valueOf(this.hearderMap.get("authsecret"));
    }
    
    public String getTimeStamp()
    {
        return null == this.hearderMap.get("timestamp") ? null : String.valueOf(this.hearderMap.get("timestamp"));
    }
    
    public Integer getServiceVersion()
    {
        return null == this.hearderMap.get("serviceversion") ? 0 : Integer.valueOf(String.valueOf(this.hearderMap.get("serviceversion")));
    }
    
    public String getServiceCode()
    {
        return null == this.hearderMap.get("servicecode") ? "" : String.valueOf(this.hearderMap.get("servicecode"));
    }
    
    public Integer getCurveFlag()
    {
        return null == this.hearderMap.get("curveflag") ? 0 : Integer.valueOf(String.valueOf(this.hearderMap.get("curveflag")));
    }
    
    public String getObjectInfo()
    {
        return null == this.hearderMap.get("objectinfo") ? "" : String.valueOf(this.hearderMap.get("objectinfo"));
    }
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
