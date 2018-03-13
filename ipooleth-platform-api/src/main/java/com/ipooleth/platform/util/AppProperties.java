package com.ipooleth.platform.util;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {



    public static  String evn;

    public static String getEvn() {
        return evn;
    }

    public static void setEvn(String evn) {
        AppProperties.evn = evn;
    }
}
