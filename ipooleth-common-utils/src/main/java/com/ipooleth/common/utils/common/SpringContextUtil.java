package com.ipooleth.common.utils.common;

import org.springframework.context.ApplicationContext;

/**
 * spring容器工具类
 *
 * 
 */
public class SpringContextUtil {

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.ctx = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return SpringContextHolder.ctx;
    }

    public static Object getBean(String beanName) {
        return SpringContextHolder.ctx.getBean(beanName);
    }

    private static class SpringContextHolder {
        private static ApplicationContext ctx;
    }

    public static <T> T getBean(Class<T> clazz) {
        return SpringContextHolder.ctx.getBean(clazz);
    }
}
