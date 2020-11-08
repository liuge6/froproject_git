package com.fandrproject.frpro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"com.fandrproject","com.fandrproject.frpro.data.dao"})
//下面此注解的实际作用是；加上此注解就能将dao下面的impl省略掉，如果不加此注解，那就乖乖的写dao 再写impl
//@MapperScan("com.fandrproject.frpro.data.dao")
public class FrproApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrproApplication.class, args);
    }

}


