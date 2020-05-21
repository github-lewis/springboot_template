package com.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 
 * Function: druidweb监控工具. <br/>
 * date: 2020年5月20日 下午4:41:50 <br/>
 *
 * @author ligc
 * @version 
 * @Copyright (c) 2020, 杭州市民卡有限公司  All Rights Reserved.
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",  
initParams={  
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源  
}  
)  
public class DruidStatFilter extends WebStatFilter {  

}  

