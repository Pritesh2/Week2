package com.SpringOct.Week2.http;

import com.SpringOct.Week2.DTO.HttpRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class HttpServiceEngine {

    private final Logger logger = LoggerFactory.getLogger(HttpServiceEngine.class);

    private final RestClient restClient;

    public String makeHttpCall(HttpRequest httpRequest)
    {
        logger.info(" Calling external API ");

        // https://docs.spring.io/spring-framework/reference/integration/rest-clients.html

        //RestClient restClient = RestClient.create();
        // This one will again create multiple object.

        //https://developer.paypal.com/api/rest/

        // where to pass client id and secret???

        try {
            String httpResponse = restClient.method(httpRequest.getHttpMethod())
                    .uri(httpRequest.getUrl())
                    .headers( (HttpHeaders restClientHeader)-> restClientHeader.addAll(httpRequest.getHttpHeaders()))
                    //.headers(new ConsumerHeaderObject(headers))
                    .body(httpRequest.getBody())
                    .retrieve()
                    .body(String.class);

            logger.info(" Paypal response after changing method signature : {}", httpResponse);

            return httpResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

/*
* Functional interface : interface with 1 abstract method
* */
