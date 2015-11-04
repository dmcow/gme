package com.slm.gme.httpserver.domain;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResponseHeadField
{
    private Map<String, Object> hearderMap = new HashMap<String, Object>();
    
    public void addHeader(String headerName, Object headerValue)
    {
        this.hearderMap.put(headerName, headerValue);
    }
    
    public Object getHeader(String HeaderName)
    {
        return this.hearderMap.get(HeaderName);
    }
    
    public String getResultCode()
    {
        return null == this.hearderMap.get("resultCode") ? null : String.valueOf(this.hearderMap.get("resultCode"));
    }
    
    public String getResultMessage()
    {
        return null == this.hearderMap.get("resultMessage") ? null
            : String.valueOf(this.hearderMap.get("resultMessage"));
    }
    
    public Set<String> getHeadKeys()
    {
        return this.hearderMap.keySet();
    }
    
}
