package com.slm.gme.framework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *POJO模型域，所有基于数据库操作的模型都需要继承该domain 
 * 
 * @author  李艳海
 * 
 * @version  [版本号, Jun 27, 2012]
 * 
 * @see  [相关类/方法]
 * 
 * @since  [产品/模块版本]
 */
public class PojoDomain extends StandardBean
{
    private static final long serialVersionUID = 1L;
    
    //属性存放器，用于扩展
    private Map<String, Object> properties = new HashMap<String, Object>();
    
    //属性显示值存放器，用于显示具体的值，比如日期转换为字符串展示，int的ID转换为具体值的展示
    private Map<String, Object> showProperties = new HashMap<String, Object>();
    
    public Integer getChangeversion()
    {
        return (Integer)this.getProperty("changeversion");
    }
    
    public void setChangeversion(Integer version)
    {
        this.addProperty("changeversion", version);
    }
    
    public Integer getChangepreversion()
    {
        return (Integer)this.getProperty("changepreversion");
    }
    
    public void setChangepreversion(Integer preversion)
    {
        this.addProperty("changepreversion", preversion);
    }
    
    /**
     * 增加一个属性，并且进行字符串的空格的去除
     * 
     * @param key
     * 
     * @param value
     * 
     * @see [类、类#方法、类#成员]
     */
    public void addProperty(String key, Object value)
    {
        addProperty(key, value, value);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @return 返回对象属性
     * @see [类、类#方法、类#成员]
     */
    public Object getProperty(String key)
    {
        Object object = properties.get(key);
        
        if (null != object && object instanceof String)
        {
            return object.toString().trim();
        }
        else
        {
            return object;
        }
    }
    
    /**
     * 增加一个属性，并且进行字符串的空格的去除
     * @param key 字段属性
     * @param value 字段值
     * @param showValue 字段的显示值
     * @see [类、类#方法、类#成员]
     */
    public void addProperty(String key, Object value, Object showValue)
    {
        if (null != value && value instanceof String)
        {
            properties.put(key, value.toString().trim());
        }
        else
        {
            properties.put(key, value);
        }
        if (null != showValue && showValue instanceof String)
        {
            showProperties.put(key, showValue.toString().trim());
        }
        else
        {
            showProperties.put(key, showValue);
        }
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @return 返回对象属性
     * @see [类、类#方法、类#成员]
     */
    public Object getShowProperty(String key)
    {
        Object object = showProperties.get(key);
        
        if (null != object && object instanceof String)
        {
            return object.toString().trim();
        }
        else
        {
            return object;
        }
    }
    
    /**
     * 重写equals方法，用于比较器进行模型比较使用,自己的模型域上就不需要进行比较了。
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (null != obj && obj instanceof PojoDomain)
        {
            PojoDomain pojoDomain = (PojoDomain)obj;
            
            if (null != this.id && null != pojoDomain.id)
            {
                return this.id.intValue() == pojoDomain.id.intValue();
            }
        }
        return false;
    }
    
    public Map<String, Object> getProperties()
    {
        return this.properties;
    }
    
    /**
     * 映射字段和界面的显示信息，此为平台规范，需要业务侧实现此函数
     * 
     * 现阶段主要用于构造changeInfo对象使用
     * 
     * @return 返回构造信息
     * 
     * @see [类、类#方法、类#成员]
     */
    protected Map<String, String> buildFieldShowValue()
    {
        Map<String, String> map = new HashMap<String, String>();
        
        return map;
    }
    
    /**
     * 调用未修改的对象的此方法构造修订记录（传入已经修改的对象）
     * 构建修订记录信息，提供标准的修订记录的模板，必须依赖于POJO对象支撑
     * @param newPojoDomain 标准的pojoDomain信息对象(修改后的pojo模型对象)
     * @return 返回修订记录信息
     * @see [类、类#方法、类#成员]
     */
    public String buildChangeInfo(PojoDomain newPojoDomain)
    {
        StringBuffer sbfResult = new StringBuffer();
        
        //只构建存在字段映射的信息，如果没有进行字段的显示映射，则不构造修订记录
        Map<String, String> buildFiledMap = buildFieldShowValue();
        
        if (null != buildFiledMap && buildFiledMap.size() > 0)
        {
            Iterator<Map.Entry<String, String>> iterator = buildFiledMap.entrySet().iterator();
            
            Map.Entry<String, String> entry = null;
            
            //构建修订记录
            while (iterator.hasNext())
            {
                entry = iterator.next();
                
                //如果信息值不为空，并且旧值和新值不一样，则构建修订记录
                if (null != this.getShowProperty(entry.getKey())
                    && null != newPojoDomain.getShowProperty(entry.getKey()))
                {
                    if (!this.getShowProperty(entry.getKey()).equals(newPojoDomain.getShowProperty(entry.getKey())))
                    {
                        if (sbfResult.length() > 0)
                        {
                            //构建分隔信息
                            sbfResult.append(" ,");
                            
                            //pojo的字段显示值信息
                            sbfResult.append(entry.getValue());
                            
                            sbfResult.append(" 从 ");
                            
                            //根据pojo的key获取当前对象的显示值信息
                            sbfResult.append(this.getShowProperty(entry.getKey()));
                            
                            sbfResult.append(" 修改为了 ");
                            
                            //根据pojo的key获取修改后的显示值信息
                            sbfResult.append(newPojoDomain.getShowProperty(entry.getKey()));
                        }
                        else
                        {
                            //pojo的字段显示值信息
                            sbfResult.append(entry.getValue());
                            
                            sbfResult.append(" 从 ");
                            
                            //根据pojo的key获取当前对象的显示值信息
                            sbfResult.append(this.getShowProperty(entry.getKey()));
                            
                            sbfResult.append(" 修改为了 ");
                            
                            //根据pojo的key获取修改后的显示值信息
                            sbfResult.append(newPojoDomain.getShowProperty(entry.getKey()));
                        }
                    }
                }
            }
        }
        return sbfResult.toString();
    }
}
