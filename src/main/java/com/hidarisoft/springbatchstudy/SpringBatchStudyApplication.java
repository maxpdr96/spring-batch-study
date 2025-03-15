package com.hidarisoft.springbatchstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;

@SpringBootApplication(exclude = {BatchAutoConfiguration.class})
public class SpringBatchStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchStudyApplication.class, args);
    }

}
