package com.SpringOct.Week2.Service.impl;

import com.SpringOct.Week2.Service.interfaces.PaymentService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

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
        return "Order created from Payment service";
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
