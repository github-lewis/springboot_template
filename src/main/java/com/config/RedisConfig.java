package com.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;



import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * Function: Redis相关配置. <br/>
 * date: 2020年5月20日 下午4:43:19 <br/>
 *
 * @author ligc
 * @version 
 * @Copyright (c) 2020, 杭州市民卡有限公司  All Rights Reserved.
 */
@Configuration
public class RedisConfig {


	@Value("${spring.redis.pool.minIdle}")
	private int minIdle;

	@Value("${spring.redis.pool.maxTotal}")
	private int maxTotal;

	@Value("${spring.redis.pool.maxWaitMills}")
	private int maxWaitMills;

	@Value("${spring.redis.pool.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.redis.pool.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.redis.pool.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.redis.pool.maxIdle}")
	private int maxIdle;

	@Value("${spring.redis.pool.blockWhenExhausted}")
	private boolean blockWhenExhausted;

	@Value("${spring.redis.pool.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.redis.pool.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.redis.pool.numTestsPerEvictionRun}")
	private int numTestsPerEvictionRun;

	@Value("${spring.redis.cluster.password}")
	private String redisPassword;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.cluster.host}")
	private String host;

	@Value("${spring.redis.cluster.host.port}")
	private int port;


	@Bean
	public RedisConnectionFactory jedisConnectionFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxWaitMillis(maxWaitMills);
		poolConfig.setTestOnBorrow(testOnBorrow);
		poolConfig.setTestOnReturn(testOnReturn);
		poolConfig.setTestWhileIdle(testWhileIdle);
		poolConfig.setBlockWhenExhausted(blockWhenExhausted);
		poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);

		jedisConnectionFactory.setHostName(host);
		jedisConnectionFactory.setPort(port);

		if (StringUtils.isNotEmpty(redisPassword)) {
			jedisConnectionFactory.setPassword(redisPassword);
		}
		jedisConnectionFactory.setTimeout(timeout);
		// 其他配置，可再次扩展

		return jedisConnectionFactory;
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}

}
