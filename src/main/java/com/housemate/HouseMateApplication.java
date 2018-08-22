package com.housemate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:Context.xml")
public class HouseMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseMateApplication.class, args);
    }
}
