package com.test;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.concurrent.ListenableFuture;

import com.SpringbootTemplateApplication;
import com.alibaba.druid.pool.DruidDataSource;
import com.service.util.JedisUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SpringbootTemplateApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestSpringbootTemplate {

	@Value("${spring.datasource.druid.url}")  
	private String dbUrl; 
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Qualifier("taskExecutor")
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private JedisUtil jedisUtil;
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	
	@Autowired
	private KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListener;
	
	@Test
	public void testDatasource() {
		DruidDataSource d = (DruidDataSource) dataSource;
		System.out.println(d.getUrl());
		Assert.assertEquals(dbUrl,d.getUrl());
	}
	
	@Test
	public void testActiveConsumer() {
		String queueName = "test.active.producer";
		String params = "hello world";
		this.jmsMessagingTemplate.convertAndSend(queueName, params);
		
		String message = jmsMessagingTemplate.receiveAndConvert(queueName, String.class);
		System.out.println(message);
		Assert.assertEquals("hello world", message);
	}
	
	@Test
	public void testThreadPool() {
		String result = "success";
		Future<String>  f = taskExecutor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return result;
			}
		});
		
		try {
			String r = f.get();
			System.out.println(r);
			Assert.assertEquals(result,r);
		}catch (InterruptedException | ExecutionException e) {
			Assert.fail();
		}
	}

	@Test
	public void testRedis(){
		
		String key = "i am key";
		String value = "i am value";
		
		Assert.assertTrue(jedisUtil.putCache(key, value));
		
		String resutl = jedisUtil.getCache(key);
		Assert.assertEquals(value, resutl);
	}
	
	@Test
	public void testkafkaSend(){
		String topic = "myTopic";
		String message = "myMessage";
		ListenableFuture<SendResult<String, String>>  f = kafkaTemplate.send(topic, message);
		try {
			Thread.sleep(2*1000);
		}catch (InterruptedException e) {
			Assert.fail();
		}
		Assert.assertTrue(f.isDone());
	}
	
}

