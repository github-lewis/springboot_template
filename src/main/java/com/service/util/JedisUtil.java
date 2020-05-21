package com.service.util;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;


@Component("jedisUtil")
public class JedisUtil {
	
	private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * getCache:获取缓存. <br/>
	 *
	 * @param key
	 * @return
	 */
	public String getCache(final String key) {
		logger.info("获取缓存key={}", key);
		String redisValue = null;
		try {
			redisValue = redisTemplate.execute(new RedisCallback<String>() {
				@Override
				public String doInRedis(RedisConnection connection) throws DataAccessException {
					RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
					byte[] rowkey = serializer.serialize(key);
					byte[] rowval = connection.get(rowkey);
					String val = serializer.deserialize(rowval);
					if (StringUtils.isNotEmpty(val)) {
						return val;
					}
					else {
						return null;
					}
				}
			});
		}
		catch (Exception e) {
			logger.error("获取缓存异常,key={}",key, e);
			return null;
		}
		return redisValue;
	}
	
	/**
	 * putCache:放入缓存. <br/>
	 *
	 * @param key
	 * @param value
	 */
	public boolean putCache(final String key, final String value) {
		logger.info("放入缓存key={},value={}", key,value);
		try {
			redisTemplate.execute(new RedisCallback<String>() {
				@Override
				public String doInRedis(RedisConnection connection) throws DataAccessException {
					RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
					byte[] rowkey = serializer.serialize(key);
					byte[] rowvalue = serializer.serialize(value);
					connection.setEx(rowkey, 1800L, rowvalue);
					return null;
				}
			});
			return true;
		}
		catch (Exception e) {
			logger.error("获取缓存异常,key={}",key, e);
			return false;
		}
	}
}

