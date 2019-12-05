package com.baizhi.cmfz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.baizhi.cmfz.dao")
@SpringBootApplication
public class CmfzApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzApplication.class, args);
    }

}
