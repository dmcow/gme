/*
 * 文  件  名：CollectionUtil.java
 * 版        权：Copyright @ vanceinfo
 * 描        述：
 * 修  改  人：张彭月
 * 修改时间：Sep 13, 2012
 * 修改内容：新增
 */
package com.slm.gme.common;

import java.util.Collection;
import java.util.Map;

/**
 * 集合处理工具类
 *
 * @author 张彭月
 * @version v1.0 
 * @since Sep 13, 2012
 */
public class CollectionUtil
{
    /**
     * 
     * 判断集合是否为空 
     *
     * @author 张彭月
     * @param collection
     * @return
     */
    public static boolean isEmpty(@SuppressWarnings("rawtypes") Collection collection)
    {
        return (collection == null || collection.isEmpty());
    }
    
    /**
     * 
     * 判断map是否为空
     *
     * @author 张彭月
     * @param map
     * @return
     */
    public static boolean isEmpty(@SuppressWarnings("rawtypes") Map map)
    {
        return (map == null || map.isEmpty());
    }
}
