package com.SpringOct.Week2.Controller;

import com.SpringOct.Week2.Service.impl.PaymentServiceImpl;
import com.SpringOct.Week2.Service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private Logger logger = LoggerFactory.getLogger(PaymentController.class);

    //private final PaymentServiceImpl paymentServiceImpl;
    private final PaymentService paymentService;

    @GetMapping("/hello")
    public String hello()
    {
        logger.info("Inside hello method of PaymentController");
        return "Hello from paypal controller";
    }

    @PostMapping("/createorder")
    public String createOrder()
    {
        logger.info("Inside create order method");
        String response = paymentService.createOrder();
        return response;
    }
}
