package com.slm.gme.httpserver;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.slm.gme.common.IConfigHelper;
import com.slm.gme.common.IConfiguration;
import com.slm.gme.common.ServiceFactory;
import com.slm.gme.framework.GmeException;
import com.slm.gme.httpserver.service.HttpServerService;


public class ConfigInitiator
{
    /** path  对应的处理分发map ，只读*/
    private static Map<String, String> serviceMap = new HashMap<String, String>();
    
    /**单例对象*/
    private static ConfigInitiator initiator = new ConfigInitiator();
    
    private static final Logger logger = Logger.getLogger(ConfigInitiator.class);
    
    private static final String HTTPSERVER_CONFIG = "gme.httpserver.config";
    
    private ConfigInitiator()
    {
    }
    
    public static ConfigInitiator getInstance()
    {
        return initiator;
    }
    
    public void init()
        throws Exception
    {
        logger.info("初始化，所有的配置文件");
        //初始化所有 配置文件，
        //通过Iconfig读取数据库的配置文件，加载数据库配置文件的相关信息
        IConfiguration configuration = IConfigHelper.getConfig(HTTPSERVER_CONFIG, false);
        
        if (configuration == null)
        {
            throw new GmeException("加载 http配置文件出错");
        }
        
        List<Object> list = configuration.getList("httpserver[@path]");
        
        for (int i = 0; i < list.size(); i++)
        {
            String path = configuration.getString("httpserver(" + i + ")[@path]");
            
            String service = configuration.getString("httpserver(" + i + ")[@serverService]");
            
            serviceMap.put(path, service);
            
        }
        
        logger.info("加载完成：" + serviceMap);
        
    }
    
    public HttpServerService getWorkService(String path)
    {
        String workServiceClass = serviceMap.get(path);
        if (workServiceClass == null)
        {
            throw new GmeException(path + " 下的 service 不存在");
        }
        return (HttpServerService)ServiceFactory.getBean(workServiceClass);
    }
}
