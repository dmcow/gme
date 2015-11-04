package com.slm.gme.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.slm.gme.notify.dao.NotifyConfigDao;
import com.slm.gme.notify.domain.NotifyConfig;

/**
 * 平台的数据库缓存
 * @author zhangziwen
 */
public final class DBCache
{
    private static Map<String, NotifyConfig> notifyConfigCodeMap = new HashMap<String, NotifyConfig>();
    
    private static Map<Integer, Set<NotifyConfig>> notifyConfigTypeMap = new HashMap<Integer, Set<NotifyConfig>>();
    
    @Autowired
    private static NotifyConfigDao notifyConfigDao;
    
    static 
    {
        refreshAllCache();
    }
    
    public static void refreshAllCache()
    {
        refreshNotifyConfigCache();
    }
    
    public static NotifyConfig getNotifyConfigByCode(String comptCode)
    {
        if (CollectionUtil.isEmpty(notifyConfigCodeMap) || !notifyConfigCodeMap.containsKey(comptCode))
        {
            refreshNotifyConfigCache();
        }
        
        return notifyConfigCodeMap.get(comptCode);
    }
    
    public static Set<NotifyConfig> getNotifyConfigByType(Integer comptType)
    {
        if (CollectionUtil.isEmpty(notifyConfigTypeMap) || !notifyConfigTypeMap.containsKey(comptType))
        {
            refreshNotifyConfigCache();
        }
        
        return notifyConfigTypeMap.get(comptType);
    }
    
    public static void refreshNotifyConfigCache(NotifyConfig notifyConfig)
    {
        if (null != notifyConfig)
        {
            notifyConfigCodeMap.put(notifyConfig.getComptCode(), notifyConfig);
            
            if (notifyConfigTypeMap.isEmpty() || !notifyConfigTypeMap.containsKey(notifyConfig.getComptType()))
            {
                notifyConfigTypeMap.put(notifyConfig.getComptType(), new HashSet<NotifyConfig>());
            }
            notifyConfigTypeMap.get(notifyConfig.getComptType()).add(notifyConfig);
        }
    }
    
    public static void refreshNotifyConfigCache()
    {
        List<NotifyConfig>  configList = notifyConfigDao.queryListByMultiCondition(new Params().toParamMap(), NotifyConfig.class);
        
        if (!CollectionUtil.isEmpty(configList))
        {
            for (NotifyConfig notifyConfig : configList)
            {
                notifyConfigCodeMap.put(notifyConfig.getComptCode(), notifyConfig);
                
                if (notifyConfigTypeMap.isEmpty() || !notifyConfigTypeMap.containsKey(notifyConfig.getComptType()))
                {
                    notifyConfigTypeMap.put(notifyConfig.getComptType(), new HashSet<NotifyConfig>());
                }
                notifyConfigTypeMap.get(notifyConfig.getComptType()).add(notifyConfig);
            }
        }
    }
}
