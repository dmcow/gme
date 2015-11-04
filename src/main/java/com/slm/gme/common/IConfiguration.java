package com.slm.gme.common;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * 统一自动集成的接口；该接口继承与Apache的Configuration的接口
 * 
 * 实现系统1+N配置的Configuration
 * 
 * @author  李艳海
 * @version  [版本号, May 23, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class IConfiguration implements Configuration
{
    private HashMap<String, String> dbMap;
    
    //使用Apache的自己实现类进行配置文件的合并
    private CompositeConfiguration compositeConfiguration = new CompositeConfiguration();
    
    public CompositeConfiguration getCompositeConfiguration()
    {
        return compositeConfiguration;
    }
    
    public void addProperty(String arg0, Object arg1)
    {
        compositeConfiguration.addProperty(arg0, arg1);
    }
    
    public void clear()
    {
        compositeConfiguration.clear();
    }
    
    public void clearProperty(String arg0)
    {
        compositeConfiguration.clearProperty(arg0);
    }
    
    public boolean containsKey(String arg0)
    {
        return compositeConfiguration.containsKey(arg0);
    }
    
    public BigDecimal getBigDecimal(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getBigDecimal(arg0);
        }
        else
        {
            return new BigDecimal(dbMap.get(arg0).toCharArray(), 0, arg0.length());
        }
    }
    
    public BigDecimal getBigDecimal(String arg0, BigDecimal arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getBigDecimal(arg0, arg1);
        }
        else
        {
            return new BigDecimal(dbMap.get(arg0).toCharArray(), 0, arg0.length());
        }
    }
    
    public BigInteger getBigInteger(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getBigInteger(arg0);
        }
        else
        {
            return new BigInteger(dbMap.get(arg0));
        }
    }
    
    public BigInteger getBigInteger(String arg0, BigInteger arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getBigInteger(arg0, arg1);
        }
        else
        {
            return new BigInteger(dbMap.get(arg0));
        }
    }
    
    public boolean getBoolean(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getBoolean(arg0);
        }
        else
        {
            return new Boolean(dbMap.get(arg0));
        }
    }
    
    public boolean getBoolean(String arg0, boolean arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getBoolean(arg0, arg1);
        }
        else
        {
            return new Boolean(dbMap.get(arg0));
        }
    }
    
    public Boolean getBoolean(String arg0, Boolean arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getBoolean(arg0, arg1);
        }
        else
        {
            return new Boolean(dbMap.get(arg0));
        }
    }
    
    public byte getByte(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getByte(arg0);
        }
        else
        {
            return Byte.valueOf(dbMap.get(arg0));
        }
    }
    
    public byte getByte(String arg0, byte arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getByte(arg0, arg1);
        }
        else
        {
            return Byte.valueOf(dbMap.get(arg0));
        }
    }
    
    public Byte getByte(String arg0, Byte arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getByte(arg0, arg1);
        }
        else
        {
            return Byte.valueOf(dbMap.get(arg0));
        }
    }
    
    public double getDouble(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getDouble(arg0);
        }
        else
        {
            return Double.valueOf(dbMap.get(arg0));
        }
    }
    
    public double getDouble(String arg0, double arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getDouble(arg0, arg1);
        }
        else
        {
            return Double.valueOf(dbMap.get(arg0));
        }
    }
    
    public Double getDouble(String arg0, Double arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getDouble(arg0, arg1);
        }
        else
        {
            return Double.valueOf(dbMap.get(arg0));
        }
    }
    
    public float getFloat(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getFloat(arg0);
        }
        else
        {
            return Float.valueOf(dbMap.get(arg0));
        }
    }
    
    public float getFloat(String arg0, float arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getFloat(arg0, arg1);
        }
        else
        {
            return Float.valueOf(dbMap.get(arg0));
        }
    }
    
    public Float getFloat(String arg0, Float arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getFloat(arg0, arg1);
        }
        else
        {
            return Float.valueOf(dbMap.get(arg0));
        }
    }
    
    public int getInt(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getInt(arg0);
        }
        else
        {
            return Integer.valueOf(dbMap.get(arg0));
        }
    }
    
    public int getInt(String arg0, int arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getInt(arg0, arg1);
        }
        else
        {
            return Integer.valueOf(dbMap.get(arg0));
        }
    }
    
    public Integer getInteger(String arg0, Integer arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getInt(arg0, arg1);
        }
        else
        {
            return Integer.valueOf(dbMap.get(arg0));
        }
    }
    
    public Iterator<String> getKeys()
    {
        return compositeConfiguration.getKeys();
    }
    
    public Iterator<String> getKeys(String arg0)
    {
        return compositeConfiguration.getKeys(arg0);
    }
    
    public List<Object> getList(String arg0)
    {
        return compositeConfiguration.getList(arg0);
    }
    
    public List<Object> getList(String arg0, List<Object> arg1)
    {
        return compositeConfiguration.getList(arg0, arg1);
    }
    
    public long getLong(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getLong(arg0);
        }
        else
        {
            return Long.valueOf(dbMap.get(arg0));
        }
    }
    
    public long getLong(String arg0, long arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getLong(arg0, arg1);
        }
        else
        {
            return Long.valueOf(dbMap.get(arg0));
        }
    }
    
    public Long getLong(String arg0, Long arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getLong(arg0, arg1);
        }
        else
        {
            return Long.valueOf(dbMap.get(arg0));
        }
    }
    
    public Properties getProperties(String arg0)
    {
        return compositeConfiguration.getProperties(arg0);
    }
    
    public Object getProperty(String arg0)
    {
        return compositeConfiguration.getProperty(arg0);
    }
    
    public short getShort(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getShort(arg0);
        }
        else
        {
            return Short.valueOf(dbMap.get(arg0));
        }
    }
    
    public short getShort(String arg0, short arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getShort(arg0, arg1);
        }
        else
        {
            return Short.valueOf(dbMap.get(arg0));
        }
    }
    
    public Short getShort(String arg0, Short arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            return compositeConfiguration.getShort(arg0, arg1);
        }
        else
        {
            return Short.valueOf(dbMap.get(arg0));
        }
    }
    
    public String getString(String arg0)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            StringBuffer sbf = new StringBuffer();
            
            //支持逗号分隔，防止了平台的默认Apache的逗号分隔的问题
            String[] results = compositeConfiguration.getStringArray(arg0);
            
            if (null != results && results.length > 0)
            {
                for (int i = 0; i < results.length; i++)
                {
                    sbf.append(results[i]);
                    if (i != results.length - 1)
                    {
                        sbf.append(",");
                    }
                }
            }
            return sbf.toString();
        }
        else
        {
            return dbMap.get(arg0);
        }
    }
    
    public String getString(String arg0, String arg1)
    {
        if (null == dbMap || null == dbMap.get(arg0))
        {
            StringBuffer sbf = new StringBuffer();
            
            //支持逗号分隔，防止了平台的默认Apache的逗号分隔的问题
            String[] results = compositeConfiguration.getStringArray(arg0);
            
            if (null != results && results.length > 0)
            {
                for (int i = 0; i < results.length; i++)
                {
                    sbf.append(results[i]);
                    if (i != results.length - 1)
                    {
                        sbf.append(",");
                    }
                }
            }
            else
            {
                sbf.append(arg1);
            }
            return sbf.toString();
        }
        else
        {
            return dbMap.get(arg0);
        }
    }
    
    public String[] getStringArray(String arg0)
    {
        return compositeConfiguration.getStringArray(arg0);
    }
    
    public boolean isEmpty()
    {
        return compositeConfiguration.isEmpty();
    }
    
    public void setProperty(String arg0, Object arg1)
    {
        compositeConfiguration.setProperty(arg0, arg1);
    }
    
    public Configuration subset(String arg0)
    {
        return compositeConfiguration.subset(arg0);
    }
    
    public HashMap<String, String> getDbMap()
    {
        return dbMap;
    }
    
    public void setDbMap(HashMap<String, String> dbMap)
    {
        this.dbMap = dbMap;
    }
}
