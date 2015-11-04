package com.slm.gme.common;


import java.util.HashMap;
import java.util.Map;

/**
 * 参数处理类
 * @ClassName: Params.java
 * @Description:
 * @author: zhangziwen
 * @Date: 2015年9月11日 上午11:59:34
 */
public class Params
{
    private Map<String, Object> paramMap = new HashMap<String, Object>();
    
    public Params()
    {
        
    }
    
    public Params(String paramName, Object paramValue)
    {
        paramMap.put(paramName, paramValue);
    }
    
    public Params putAll(Map<String, Object> params)
    {
        if (null != params && !CollectionUtil.isEmpty(params))
        {
            paramMap.putAll(params);
        }
        
        return this;
    }
    
    public Params put(String paramName, Object paramValue)
    {
        if (null != paramValue)
        {
            paramMap.put(paramName, paramValue);
        }
        
        return this;
    }
    
    public Params putStr(String paramName, String paramValue)
    {
        if (CommonUtil.isNotEmpty(paramValue))
        {
            paramMap.put(paramName, paramValue);
        }
        
        return this;
    }
    
    public Params putBegin(Integer begin)
    {
        paramMap.put("begin", begin);
        
        return this;
    }
    
    public Params putOrder(String order)
    {
        paramMap.put("order", order);
        
        return this;
    }
    
    public Params putDefaultBegin()
    {
        paramMap.put("begin", 0);
        
        return this;
    }
    
    public Params putPageSize(Integer pageSize)
    {
        paramMap.put("pageSize", pageSize);
        
        return this;
    }
    
    public Params putDefaultPageSize()
    {
        paramMap.put("pageSize", 10);
        
        return this;
    }
    
    public boolean isEmpty()
    {
        return paramMap.isEmpty();
    }
    
    public Map<String, Object> toParamMap()
    {
        return paramMap;
    }
}
