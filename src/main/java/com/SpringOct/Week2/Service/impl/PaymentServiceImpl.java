package com.SpringOct.Week2.Service.impl;

import com.SpringOct.Week2.Service.TokenService;
import com.SpringOct.Week2.Service.interfaces.PaymentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final TokenService tokenService;

    @Override
    public String createOrder() {
        logger.info(" ================== create order method ============== ");
        /*
         TODO
         * getAccessToken
         * Call paypal createOrder
          Success/Failure/Timeout - proper request handling
          What to return to your calling service
        */

        String accessToken = tokenService.getAccessToken();
        logger.info(" Received token from Token service : {}", accessToken);
        //return "Order created from Payment service";
        return accessToken;
    }

    public void method2()
    {
        logger.info(" ========= Method2 : Not to be exposed to outer world");
    }

    public void method3()
    {
        logger.info(" ========= Method3 : Not to be exposed to outer world");
    }

    @PostConstruct
    public void init()
    {
        logger.info(" =================== Payment service impl now maintained by beans ================== {}", PaymentServiceImpl.class);
    }
}
