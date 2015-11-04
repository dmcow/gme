
package com.slm.gme.common;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: JedisUtil.java
 * @Description: redis客户端工具
 * @author: zhangziwen
 * @Date: 2015年9月10日 上午11:45:23
 */
public class JedisUtil
{
    private static String ip = "120.27.28.48";

    private static int port = 6379;

    private static String password = "admin";

    private static Jedis jedis = new Jedis(ip, port);

    static
    {
        jedis.auth(password);
    }

    /**
     * @Description: 删除健为key的缓存
     * @param key健
     */
    public static void del(String key)
    {
        jedis.del(key);
    }

    /**
     * @Description: 删除健为多个缓存
     * @param keys多个健
     */
    public static void del(String... keys)
    {
        jedis.del(keys);
    }

    /**
     * @Description:判断是否存在健为key的缓存
     * @param key健
     * @return true:存在 false:不存在
     */
    public static boolean exists(String key)
    {
        return jedis.exists(key);
    }

    /**
     * @Description: 向redis缓存中放对象
     * @param key健
     * @param object为String或可转换为JSON的标准对象
     */
    public static void put(String key , Object object)
    {

        String value = JSONObject.toJSONString(object);

        jedis.set(key, value);
    }

    /**
     * @Description: 向缓存中放列表数据
     * @param key健
     * @param strList值列表
     */
    public static <T> void putList(String key , List<T> objList)
    {
        if (null != objList && !objList.isEmpty())
        {
            // 先清空
            del(key);

            for (T obj : objList)
            {
                String value = JSONObject.toJSONString(obj);

                jedis.rpush(key, value);
            }
        }
    }

    /**
     * @Description: 向缓存中放集合数据
     * @param key健
     * @param strList值列表
     */
    public static <T> void putSet(String key , Set<T> objSet)
    {
        if (null != objSet && !objSet.isEmpty())
        {
            // 先清空
            del(key);

            for (T obj : objSet)
            {
                String value = JSONObject.toJSONString(obj);

                jedis.sadd(key, value);
            }
        }
    }

    /**
     * @Description: 从redis缓存中取值
     * @param key健
     * @return 值
     */
    public static String get(String key)
    {
        return jedis.get(key);
    }

    public static List<String> getList(String key)
    {
        return jedis.lrange(key, 0, -1);
    }

    public static Set<String> getSet(String key)
    {
        return jedis.smembers(key);
    }

    public static void main(String[] args)
    {
        //String value = "小石头 哈哈哈哈哈";
//        put("symlich_name", value);
        del("symlich_name");
    }
}
