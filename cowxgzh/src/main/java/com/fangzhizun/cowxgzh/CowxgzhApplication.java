package com.fangzhizun.cowxgzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@ServletComponentScan
public class CowxgzhApplication {

    public static void main(String[] args) {
        SpringApplication.run(CowxgzhApplication.class, args);
    }

}
