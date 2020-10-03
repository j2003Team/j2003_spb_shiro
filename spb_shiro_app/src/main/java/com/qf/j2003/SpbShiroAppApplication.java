package com.qf.j2003;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.qf.j2003.mapper"})
public class SpbShiroAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbShiroAppApplication.class, args);
    }

}
