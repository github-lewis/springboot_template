package com.filter;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * 
 * Function: druidweb监控工具. <br/>
 * date: 2020年5月20日 下午4:41:23 <br/>
 *
 * @author ligc
 * @version 
 * @Copyright (c) 2020, 杭州市民卡有限公司  All Rights Reserved.
 */

@WebServlet(urlPatterns="/druid/*",  
initParams={  
     @WebInitParam(name="allow",value="${druid.stat.allow}"),// IP白名单(没有配置或者为空，则允许所有访问)  
     @WebInitParam(name="deny",value="${druid.stat.deny}"),// IP黑名单 (存在共同时，deny优先于allow)  
     @WebInitParam(name="loginUsername",value="${druid.stat.name}"),// 用户名  
     @WebInitParam(name="loginPassword",value="${druid.stat.password}"),// 密码  
     @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能  
})  
public class DruidStatViewServlet extends StatViewServlet {  
	private static final long serialVersionUID = -2688872071445249539L;  

}  

