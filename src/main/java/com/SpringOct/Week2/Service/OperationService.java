package com.SpringOct.Week2.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class OperationService {

    public static Logger logger = LoggerFactory.getLogger(OperationService.class);

    //@Autowired
    private final Random randomObject;

//    public OperationService(Random randomObject) {
//        this.randomObject = randomObject;
//    }

//    OperationService()
//    {
//        logger.info("=========== Operation service object created by beans =============");
//    }

    public int addNumbers(int val1, int val2)
    {
        logger.info(" ============== Random no is {} =================", randomObject.nextInt());
        return (val1 + val2);
    }
}
