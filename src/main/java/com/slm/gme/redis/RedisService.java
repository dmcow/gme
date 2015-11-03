
package com.slm.gme.redis;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: RedisService
 * @Description: jedis数据库操作接口
 * @author Administrator
 * @date 2015年9月8日 下午5:51:16
 */
public interface RedisService
{
    /**
     * @Description: 设置key/value
     * @throws
     * zhangziwen
     */
    public void set(String key , String value , long liveTime);

    /**
     * @Description: 设置key/value
     * @throws
     * zhangziwen
     */
    public void set(String key , String value);

    /**
     * @Description: 设置key/value
     * @throws
     * zhangziwen
     */
    public void set(byte[] key , byte[] value);

    /**
     * @Description: 设置key/value
     * @throws
     * zhangziwen
     */
    public void set(final byte[] key , final byte[] value , final long liveTime);

    /**
     * @Description: 删除key/value
     * @throws
     * zhangziwen
     */
    public void delete(String... keys);

    /**
     * @Description: 删除key/value
     * @throws
     * zhangziwen
     */
    public <T> void delete(List<T> lkey);

    /**
     * @Description: 删除模糊匹配删除
     * @throws
     * zhangziwen
     */
    public <T> void deleteAll(String pattern);

    /**
     * @Description: 删除key/value
     * @throws
     * zhangziwen
     */
    public void delete(String key);

    /**
     * @Description: 获取value返回为string类型
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public String get(final String key);

    /**
     * @Description: 查看是否存在
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public Boolean exists(final String key);

    /**
     * @Description: 清空数据库 慎用
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public void flushDB();

    /**
     * @Description: 数据库key数量
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public Long dbSize();

    /**
     * @Description: 接通检测
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public String ping();

    /**
     * @Description: 递增序列
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public Long incr(final String key);

    /**
     * @Description: 获取多个key的value
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public List<String> mget(final String... keys);

    /**
     * @Description: 获取多个key和value
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public Map<String, String> keysAndValues(final String pattern);

    /**
     * @Description: 集合数据保存
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public Long lpush(final String key , final String... values);

    /**
     * @Description: 集合数据保存
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public List<String> lRange(final String key , final long begin , final long end);

    /**
     * @Description: 集合总数保存
     * @throws UnsupportedEncodingException
     * @author Administrator
     */
    public Long lLen(final String key);
}
