package com.example.demo.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InitialController {
    
    @GetMapping
    private String initial(){
        return "HELLO";
    }
}
