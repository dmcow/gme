package com.slm.gme.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * service工厂，可以直接获取到spring中的配置
 * 
 * @author  李艳海
 * @version  [版本号, Jun 1, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ServiceFactory implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;
    
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        ServiceFactory.applicationContext = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }
    
    public static Object getBean(String name)
        throws BeansException
    {
        return applicationContext.getBean(name);
    }
    
    @SuppressWarnings("unchecked")
    public static Object getBean(String name, @SuppressWarnings("rawtypes") Class requiredType)
        throws BeansException
    {
        return applicationContext.getBean(name, requiredType);
    }
    
    public static boolean containsBean(String name)
    {
        return applicationContext.containsBean(name);
    }
    
    public static boolean isSingleton(String name)
        throws NoSuchBeanDefinitionException
    {
        return applicationContext.isSingleton(name);
    }
    
    @SuppressWarnings("rawtypes")
    public static Class getType(String name)
        throws NoSuchBeanDefinitionException
    {
        return applicationContext.getType(name);
    }
    
    public static String[] getAliases(String name)
        throws NoSuchBeanDefinitionException
    {
        return applicationContext.getAliases(name);
    }
}
