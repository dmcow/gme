package com.slm.gme.common;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 统一配置工具类
 * 
 * @author  李艳海
 * @version  [版本号, May 23, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class IConfigHelper
{
    //统一配置文件 
    private static HashMap<String, String> ICONFIGFILEMAP = new HashMap<String, String>();
    
    //统一配置实例
    private static HashMap<String, IConfiguration> ICONFIGINSTANCEMAP = new HashMap<String, IConfiguration>();
    
    private String configName;
    
    /**
     * 获取统一配置文件中的配置
     * @param confName 配置文件前缀名
     * @param isFlush 是否需要做实施刷新的标示（一般情况下都不做实时刷新，因为实时新性能非常低，如果不做实时刷新，则直接读取内存中加载的数据即可）
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static IConfiguration getConfig(String confName, boolean isFlush)
    {
        IConfiguration config = null;
        
        //如果需要动态刷新或者配置文件第一次加载则需要进行配置
        if (isFlush || null == ICONFIGINSTANCEMAP.get(confName))
        {
            config = new IConfiguration();
            try
            {
                if (null != ICONFIGFILEMAP.get(confName))
                {
                    String fileName = String.valueOf(ICONFIGFILEMAP.get(confName));
                    
                    //目前只支撑XML和properties的配置  其他模式暂时不支撑
                    if (null != fileName && fileName.endsWith(".xml"))
                    {
                        config.getCompositeConfiguration().addConfiguration(new XMLConfiguration(
                            IConfigHelper.class.getClassLoader().getResource(fileName)));
                    }
                    if (null != fileName && fileName.endsWith(".properties"))
                    {
                        config.getCompositeConfiguration().addConfiguration(new PropertiesConfiguration(
                            IConfigHelper.class.getClassLoader().getResource(fileName)));
                    }
                }
            }
            catch (ConfigurationException e)
            {
                e.printStackTrace();
            }
            ICONFIGINSTANCEMAP.put(confName, config);
        }
        else
        {
            config = ICONFIGINSTANCEMAP.get(confName);
        }
        
        return config;
    }
    
    /**
     * 返回配置的文件的名称
     * @param confName iocnfig的配置名称
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getConfigFile(String confName)
    {
        return ICONFIGFILEMAP.get(confName);
    }
    
    /**
     * 加载所有在配置文件中的iconfig的配置
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public void loadAllconfigInFile()
    {
        System.out.println("---- init Iconfig in file begin----");
        
        SAXReader reader = new SAXReader();
        try
        {
            //读取配置文件流
            Document document = reader.read(this.getClass().getClassLoader().getResourceAsStream(this.getConfigName()));
            
            Element rootElement = document.getRootElement();
            
            List<Element> allConfig = rootElement.elements("config");
            
            if (null != allConfig && allConfig.size() > 0)
            {
                for (Element element : allConfig)
                {
                    ICONFIGFILEMAP.put(element.attributeValue("name"), element.attributeValue("path"));
                }
            }
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        System.out.println("---- init Iconfig in file end----");
    }
    
    public String getConfigName()
    {
        return configName;
    }
    
    public void setConfigName(String configName)
    {
        this.configName = configName;
    }
    
    /**
     * 测试Iconfig
     * @param ss
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] ss)
    {
        IConfigHelper configHepler = new IConfigHelper();
        
        configHepler.setConfigName("iconfig.xml");
        
        //初始化iconfig
        configHepler.loadAllconfigInFile();
        
    }
}
