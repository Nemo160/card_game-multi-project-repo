package com.cardgame.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class CardGameBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardGameBackendApplication.class, args);
    }
    @GetMapping("/api")
    public String hello(){
        return "Hello world";
    }
    @GetMapping("/")
    public String hey(){
        return "Hey";
    }
}
