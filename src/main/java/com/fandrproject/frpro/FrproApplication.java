package com.fandrproject.frpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"com.fandrproject","com.fandrproject.frpro.data.dao"})
@EnableScheduling
public class FrproApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrproApplication.class, args);
    }

}