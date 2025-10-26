package com.SpringOct.Week2.Service;

import com.SpringOct.Week2.Constant.Constant;
import com.SpringOct.Week2.DTO.HttpRequest;
import com.SpringOct.Week2.http.HttpServiceEngine;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private final HttpServiceEngine httpServiceEngine;

    @Value("${paypal.outh.url}")
    private String outhUrl = "";
    @Value("${paypal.client.id}")
    private String clientId = "";
    @Value("${paypal.client.secret}")
    private String clientSecret = "";

    // TODO : In future save it in Redis
    private static String accessToken;

    public String getAccessToken()
    {
        if (accessToken != null)
        {
            logger.info(" Returing the saved access Token");
            return accessToken;
        }

        // TODO : implement logic to get access token

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        class ConsumerHeaderObject implements Consumer<HttpHeaders> {

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
        formData.add(Constant.GRANT_TYPE, Constant.CLIENT_CREDENTIALS);

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setHttpMethod(HttpMethod.POST);
        httpRequest.setUrl(outhUrl);
        httpRequest.setHttpHeaders(headers);
        httpRequest.setBody(formData);

        accessToken = httpServiceEngine.makeHttpCall(httpRequest);
        //return "Access Token" + response;
        return accessToken;
    }

}
