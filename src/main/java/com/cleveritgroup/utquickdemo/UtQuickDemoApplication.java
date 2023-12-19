package com.cleveritgroup.utquickdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class UtQuickDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtQuickDemoApplication.class, args);
    }

}
