package com.hidarisoft.springbatchstudy;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {BatchAutoConfiguration.class})
public class SpringBatchStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchStudyApplication.class, args);
    }

}
