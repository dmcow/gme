package com.slm.gme.httpserver.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NamedParameter
{
    private String key;
    
    private String value;
    
    public String getKey()
    {
        return key;
    }
    
    public void setKey(String key)
    {
        this.key = key;
    }
    
    public String getValue()
    {
        return value;
    }
    
    public void setValue(String value)
    {
        this.value = value;
    }
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
