package com.SpringOct.Week2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Random;

@Configuration
public class AppConfig {

    public static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    Random randomObject(){
        Random obj = new Random();
        logger.info(" ============== Random object created =============== {}", obj);
        return obj;
    }

    @Bean
    RestClient restClient()
    {
        return RestClient.create();
    }
}
