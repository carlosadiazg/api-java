package com.davivienda.monedero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonederoApplication {

    public static void main(String[] args) {
        System.out.println("main running");
        SpringApplication.run(MonederoApplication.class, args);
    }

}