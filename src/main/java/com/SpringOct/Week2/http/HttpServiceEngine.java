package com.SpringOct.Week2.http;

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

    public String makeHttpCall()
    {
        logger.info(" Calling external API ");

        // https://docs.spring.io/spring-framework/reference/integration/rest-clients.html

        //RestClient restClient = RestClient.create();
        // This one will again create multiple object.

        //https://developer.paypal.com/api/rest/

        // where to pass client id and secret???

        HttpHeaders headers = new HttpHeaders();
        String clientId = "AVt7kaDw51ZzYMOSRY-t1iYqjaqbF-P6e5VoKCH6z56nKcryaVHEJcN0G5DkVBD4bJwfFcbmOnutiVqq";
        String clientSecret = "ELTLzjtQrZVO3S8DxOqWj-SYDgDQe2W87yiogwUMTPA7f7sPoJPDYK34jyY3XFjq-7kKveeLDu9-mXw4";

        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        class ConsumerHeaderObject implements Consumer<HttpHeaders>{

            HttpHeaders applicationHeaders;

            ConsumerHeaderObject(HttpHeaders applicationHeaders)
            {
                this.applicationHeaders = applicationHeaders;
            }

            @Override
            public void accept(HttpHeaders httpHeaders) {
                httpHeaders.addAll(this.applicationHeaders);
            }
        }

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type","client_credentials");


        String httpResponse = restClient.method(HttpMethod.POST)
                .uri("https://api-m.sandbox.paypal.com/v1/oauth2/token")
                .headers(new ConsumerHeaderObject(headers))
                .body(formData)
                .retrieve()
                .body(String.class);

        logger.info(" Paypal response : {}", httpResponse);

        return httpResponse;
    }
}
