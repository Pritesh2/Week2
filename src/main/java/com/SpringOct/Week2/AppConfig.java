package com.SpringOct.Week2;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
    RestClient restClient_old()
    {
        return RestClient.create();
    }

    @Bean
    RestClient restClient(RestClient.Builder builder) {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(100);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(TimeValue.ofSeconds(30))
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectionRequestTimeout(10000); // 10 seconds - time to get connection from pool
        requestFactory.setConnectTimeout(10000);  // 10 seconds - time to establish TCP connection
        requestFactory.setReadTimeout(15000);     // 15 seconds - time waiting for server response

        return builder
                .requestFactory(requestFactory)
                .build();
    }

}
