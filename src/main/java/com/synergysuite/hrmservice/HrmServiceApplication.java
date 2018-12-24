package com.synergysuite.hrmservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Clock;
import java.time.LocalDate;

@SpringBootApplication
public class HrmServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrmServiceApplication.class, args);
    }
}