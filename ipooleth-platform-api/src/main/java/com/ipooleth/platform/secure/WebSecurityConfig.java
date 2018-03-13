package com.ipooleth.platform.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private WebSecurityInterceptor webSecurityInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(webSecurityInterceptor).addPathPatterns("/**")
        registry.addInterceptor(webSecurityInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/public/**").excludePathPatterns("/api/**")
                .excludePathPatterns("/error").excludePathPatterns("/login")
                .excludePathPatterns("/test/**").excludePathPatterns("/web/**").
                excludePathPatterns("/user/**").excludePathPatterns("/rest/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/html/**").addResourceLocations("classpath:/myhtml/");
        registry.addResourceHandler("/public/**").addResourceLocations("/html/");
        super.addResourceHandlers(registry);
    }


}
