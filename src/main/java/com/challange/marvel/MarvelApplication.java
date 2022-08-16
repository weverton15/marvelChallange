package com.challange.marvel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties
@EnableWebMvc
public class MarvelApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarvelApplication.class, args);
    }

}
