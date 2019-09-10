package com.youyu.cotenant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.youyu.cotenant.repository.biz.repository","com.youyu.cotenant.repository.biz.repository.biz"})
public class CotenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(CotenantApplication.class, args);
    }

}
