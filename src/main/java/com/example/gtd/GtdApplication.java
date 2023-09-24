package com.example.gtd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class GtdApplication {

    static final Logger log = LoggerFactory.getLogger(GtdApplication.class);
    public static void main(String[] args) {
        log.info("Before starting application");
        SpringApplication.run(GtdApplication.class, args);
        log.info("After starting application");
    }

}
