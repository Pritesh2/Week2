package com.SpringOct.Week2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week2Application {

	public static Logger logger = LoggerFactory.getLogger(Week2Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Week2Application.class, args);
		logger.info("=========== Main class ====== 1st ======== ");
	}

}
