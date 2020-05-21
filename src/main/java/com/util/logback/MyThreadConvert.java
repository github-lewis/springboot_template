package com.util.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Function: 日志线程打印 <br/>
 * Date: 2017年3月7日 上午10:31:31 <br/>
 *
 * @author ray
 * @version 1.0
 * @Copyright (c) 2017, 杭州市民卡有限公司  All Rights Reserved.
 */
public class MyThreadConvert extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		return event.getThreadName().split("\\|")[0];
	}

}

