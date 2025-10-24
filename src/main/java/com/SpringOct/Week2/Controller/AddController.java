package com.SpringOct.Week2.Controller;


import com.SpringOct.Week2.DTO.AddRequest;
import com.SpringOct.Week2.Service.OperationService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddController {

    public static Logger logger = LoggerFactory.getLogger(AddController.class);

    @Autowired
    ApplicationContext applicationContext;

    //@Autowired
    OperationService addition;

    private final Gson gson;

    //addition = applicationContext.get
//    public AddController(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }

    //@Autowired
//    AddController(OperationService operationService)
//    {
//        logger.info(" ====== AddController class created {}",AddController.class);
//        this.addition = operationService;
//    }

    @GetMapping("/hello")
    private String hello()
    {
        logger.info(" ============== API hello called ============= ");
        return "Hello from API 4th time";
    }

    @PostMapping("/add")
    private String addNumbers(@RequestBody AddRequest requestBody)
    {
        logger.info(" ============== Method to add 2 numbers");
        // API will have payload ie first and second

        logger.info(" ============ Recevied values : value1 = {}, value2 = {}", requestBody.getValue1(), requestBody.getValue2());

        // perform operation this is in service class
        //OperationService addition = new OperationService();
        addition = applicationContext.getBean(OperationService.class);
        int val1 = requestBody.getValue1();
        int val2 = requestBody.getValue2();
        int ans = addition.addNumbers(val1, val2);

        logger.info("==================== Operationservice instance via objectname is : {}",addition );

        // POJO to json
        //Gson gson = new Gson();
        String jsonToString = gson.toJson(requestBody);
        logger.info("================== We just used GSON package in our project ============ {}", jsonToString);

        return "Addition of 2 numbers : " + ans;


    }
}
