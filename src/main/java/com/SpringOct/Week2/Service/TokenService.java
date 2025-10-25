package com.SpringOct.Week2.Service;

import com.SpringOct.Week2.http.HttpServiceEngine;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private final HttpServiceEngine httpServiceEngine;

    private static String accessToken;

    public String getAccessToken()
    {
        if (accessToken != null)
        {
            logger.info(" Returing the saved access Token");
            return accessToken;
        }

        // TODO : implement logic to get access token
        String response = httpServiceEngine.makeHttpCall();
        return "Access Token" + response;
    }

}
