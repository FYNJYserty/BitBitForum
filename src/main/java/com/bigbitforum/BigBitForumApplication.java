package com.bigbitforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BigBitForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigBitForumApplication.class, args);
    }

}
