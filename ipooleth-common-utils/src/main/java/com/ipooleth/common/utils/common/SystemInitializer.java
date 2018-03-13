package com.ipooleth.common.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 获取spring上下文
 *
 * 
 */
@Component
public class SystemInitializer implements ApplicationListener<ApplicationEvent> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationContext ctx;

	public void onApplicationEvent(ApplicationEvent event) {
		if (ContextRefreshedEvent.class.isInstance(event) && event.getSource().toString().contains("startup date")) {
			logger.info("---System is initializaing... ---");
			initSpringContextUtil();

		}
	}

	private void initSpringContextUtil() {
		SpringContextUtil.setApplicationContext(ctx);
	}


}
