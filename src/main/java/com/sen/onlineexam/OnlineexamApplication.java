package com.sen.onlineexam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sen.onlineexam.dao")
public class OnlineexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineexamApplication.class, args);
    }

}
