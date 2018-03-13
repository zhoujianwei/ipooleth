package com.ipooleth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableRedisHttpSession
@SpringBootApplication(scanBasePackages = {"com.ipooleth"})
public class IpoolethPlatformApplication extends SpringBootServletInitializer implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(IpoolethPlatformApplication.class);

    @Autowired
    private RestTemplateBuilder builder;

    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

    public void run(String... arg0) throws Exception {
        logger.info("runing......");
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("SpringApplicationBuilder ........init ....");
        return application.sources(IpoolethPlatformApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IpoolethPlatformApplication.class, "--debug");
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;
        public int getExitCode() {
            return 10;
        }

    }

}
