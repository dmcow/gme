
package com.slm.gme.redis;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.slm.gme.common.CommonUtil;

/**
 * redis操作接口
 * 
 * @ClassName: RedisServiceImpl
 * @Description: TODO
 * @author zhangziwen
 * @date 2015年9月8日 下午5:48:18
 **/

@Service
public class RedisServiceSupport implements RedisService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * @Description: 设置key/value
	 * @throws 
	 * @author zhangziwen
	 */
	public void set(String key, String value, long liveTime) {

		this.set(getRedisSerializer().serialize(key), getRedisSerializer().serialize(value), liveTime);
	}

	/**
	 * @Description: 设置key/value
	 * @throws @author
	 * zhangziwen
	 */
	public void set(String key, String value) {
		this.set(key, value, 0L);
	}

	/**
	 * @Description: 设置key/value
	 * @throws @author
	 * zhangziwen
	 */
	public void set(byte[] key, byte[] value) {
		this.set(key, value, 0L);
	}

	/**
	 * @Description: 设置key/value
	 * @throws @author
	 * zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public void set(final byte[] key, final byte[] value, final long liveTime) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(key, value);
				if (liveTime > 0) {
					connection.expire(key, liveTime);
				}
				return 1L;
			}
		});
	}

	/**
	 * @Description: 删除key/value
	 * @throws @author
	 * zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public void delete(String... keys) {
		redisTemplate.delete(Arrays.asList(keys));
	}

	/**
	 * @Description: 删除key/value
	 * @throws @author
	 * zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public <T> void delete(List<T> lkey) {
		redisTemplate.delete(lkey);
	}

	/**
	 * @Description: 删除key/value
	 * @throws @author
	 * zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * @Description: 获取value返回为string类型
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public String get(final String key) {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				try {
					byte[] by = connection.get(key.getBytes());
					if(CommonUtil.isNull(by)){
						return null;
					}else{
					return new String(by, "utf-8");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	
	/**
	 * @Description: 获取多个key的value
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public List<String> mget(final String ... keys) {
		final byte[][] b = new byte[keys.length][];
		int i=0;
		for(String str :keys){
			if(CommonUtil.isNotEmpty(str)){
				b[i] = str.getBytes();
			}
			i++;
		}
		return  (List<String>) redisTemplate.execute(new RedisCallback<Object>() {
			public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
				List<byte[]> lb = connection.mGet(b);
				List<String> ls = new ArrayList<String>();
				for(byte[] bt:lb){
					try {
						ls.add(new String(bt,"utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				return ls;
			}
		});
	}
	
	/**
	 * @Description: 获取多个key和value
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> keysAndValues(final String pattern){
		Set<String> set = redisTemplate.keys(pattern);
		Map<String,String> map  = new HashMap<String,String>();
		for(String str : set){
			if(CommonUtil.isNotEmpty(str)){
				map.put(str,get(str));
			}
		}
		return map;
	} 
	
	
	/**
	 * @Description: 查看是否存在
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public Boolean exists(final String key) {
		return (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(getRedisSerializer().serialize(key));
			}
		});
	}

	/**
	 * @Description: 清空数据库 慎用
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public void flushDB() {
		redisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return null;
			}
		});
	}

	/**
	 * @Description: 数据库key数量
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public Long dbSize() {
		return (Long) redisTemplate.execute(new RedisCallback<Object>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	/**
	 * @Description: 接通检测
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public String ping() {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.ping();
			}
		});
	}

	/**
	 * @Description: 递增序列
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public Long incr(final String key) {
		return (Long) redisTemplate.execute(new RedisCallback<Object>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.incr(getRedisSerializer().serialize(key));
			}
		});
	}

	/**
	 * @Description: 序列化
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	private RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}
	
	public <T> void deleteAll(String pattern) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @Description: 集合数据保存
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public Long lpush(final String key,final String ...values){
		final byte[][] bvalue = new byte[values.length][];
		int i=0;
		for(String str :values){
			if(CommonUtil.isNotEmpty(str)){
				bvalue[i] = str.getBytes();
			}
			i++;
		}
		return (Long) redisTemplate.execute(new RedisCallback<Object>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.rPush(getRedisSerializer().serialize(key), bvalue);
			}
		});
	}
	
	/**
	 * @Description: 集合数据保存
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public List<String> lRange(final String key,final long begin,final long end){
		return (List<String>) redisTemplate.execute(new RedisCallback<Object>() {
			public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
				List<byte[]> by =  connection.lRange(getRedisSerializer().serialize(key), begin, end);
				List<String> li = new ArrayList<String>();
				for(byte[] b:by){
					try{
						li.add(new String(b,"utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				return li;
			}
		});
	}
	
	/**
	 * @Description: 集合总数保存
	 * @throws UnsupportedEncodingException
	 * @author zhangziwen
	 */
	@SuppressWarnings("unchecked")
	public Long lLen(final String key){
		return (Long) redisTemplate.execute(new RedisCallback<Object>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.lLen(getRedisSerializer().serialize(key));
			}
		});
	}
}
