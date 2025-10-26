package com.SpringOct.Week2.Service;

import com.SpringOct.Week2.DTO.HttpRequest;
import com.SpringOct.Week2.http.HttpServiceEngine;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        String clientId = "AVt7kaDw51ZzYMOSRY-t1iYqjaqbF-P6e5VoKCH6z56nKcryaVHEJcN0G5DkVBD4bJwfFcbmOnutiVqq";
        String clientSecret = "ELTLzjtQrZVO3S8DxOqWj-SYDgDQe2W87yiogwUMTPA7f7sPoJPDYK34jyY3XFjq-7kKveeLDu9-mXw4";

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
        formData.add("grant_type","client_credentials");

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setHttpMethod(HttpMethod.POST);
        httpRequest.setUrl("https://api-m.sandbox.paypal.com/v1/oauth2/token");
        httpRequest.setHttpHeaders(headers);
        httpRequest.setBody(formData);

        accessToken = httpServiceEngine.makeHttpCall(httpRequest);
        //return "Access Token" + response;
        return accessToken;
    }

}
