package com.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 * Function: spring线程池相关配置. <br/>
 * date: 2020年5月20日 下午4:43:28 <br/>
 *
 * @author ligc
 * @version 
 * @Copyright (c) 2020, 杭州市民卡有限公司  All Rights Reserved.
 */
@Configuration  
public class TaskExecutorConfig {  

	@Value("${spring.threadpool.allowCoreThreadTimeOut}") 
    private boolean allowCoreThreadTimeOut;
	@Value("${spring.threadpool.awaitTerminationSeconds}") 
	private int awaitTerminationSeconds;
	@Value("${spring.threadpool.corePoolSize}") 
	private int corePoolSize;
	@Value("${spring.threadpool.keepAliveSeconds}") 
	private int keepAliveSeconds;
	@Value("${spring.threadpool.maxPoolSize}") 
	private int maxPoolSize;
	@Value("${spring.threadpool.queueCapacity}") 
	private int queueCapacity;
	@Value("${spring.threadpool.threadGroupName}") 
	private String threadGroupName;
	@Value("${spring.threadpool.threadNamePrefix}") 
	private String threadNamePrefix;
	@Value("${spring.threadpool.waitForJobsToCompleteOnShutdown}") 
	private boolean waitForJobsToCompleteOnShutdown;

	@Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){  
    	ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    	taskExecutor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
    	taskExecutor.setAwaitTerminationSeconds(awaitTerminationSeconds);
    	taskExecutor.setCorePoolSize(corePoolSize);
    	taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
    	taskExecutor.setMaxPoolSize(maxPoolSize);
    	taskExecutor.setQueueCapacity(queueCapacity);
    	taskExecutor.setThreadGroupName(threadGroupName);
    	taskExecutor.setThreadNamePrefix(threadNamePrefix);
    	taskExecutor.setWaitForTasksToCompleteOnShutdown(waitForJobsToCompleteOnShutdown);
    	taskExecutor.setQueueCapacity(queueCapacity);
    	return taskExecutor;
    }
    
}

