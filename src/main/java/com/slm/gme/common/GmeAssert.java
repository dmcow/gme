package com.slm.gme.common;


import java.util.Collection;

import com.slm.gme.framework.GmeException;


/**
 * 平台断言类
 * @author  symlich
 * @version  [版本号, May 22, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class GmeAssert
{
    public static void notNull(Object object)
    {
        if (null == object)
        {
            throw new GmeException("Object is null!");
        }
    }
    
    public static void notNull(Object object, String errMsg)
    {
        if (null == object)
        {
            throw new GmeException(errMsg);
        }
    }
    
    public static void equals(Object obj1, Object obj2)
    {
        notNull(obj1);
        notNull(obj2);
        if (!obj1.equals(obj2))
        {
            throw new GmeException("Object not equals!");
        }
    }
    
    public static void isTrue(boolean value)
    {
        if (!value)
        {
            throw new GmeException("boolean value assert fail!");
        }
    }
    
    public static void isFalse(boolean value)
    {
        if (value)
        {
            throw new GmeException("boolean value assert fail!");
        }
    }
    
    public static void isEmpty(@SuppressWarnings("rawtypes") Collection collection)
    {
        if (!CollectionUtil.isEmpty(collection))
        {
            throw new GmeException("collection assert empty fail!");
        }
    }
    
    public static void isNotEmpty(@SuppressWarnings("rawtypes") Collection collection)
    {
        if (CollectionUtil.isEmpty(collection))
        {
            throw new GmeException("collection assert not empty fail!");
        }
    }
}
