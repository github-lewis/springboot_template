package com.config;


import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;


/**
 * 
 * Function: druid数据源配置. <br/>
 * date: 2020年5月20日 下午4:41:59 <br/>
 *
 * @author ligc
 * @version 
 * @Copyright (c) 2020, 杭州市民卡有限公司  All Rights Reserved.
 */
@Configuration  
public class DruidDBConfig {  

    @Primary  //在同样的DataSource中，首先使用被标注的DataSource 
    @Bean(name = "dataSource")
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSource(){  
    	return DruidDataSourceBuilder.create().build(); 
    }

}  

