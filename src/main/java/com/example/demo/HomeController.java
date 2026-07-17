package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Demo v2 running in Docker Swarm";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot";
    }
}