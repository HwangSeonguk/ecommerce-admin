package com.example.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;

@RestController
public class HelloController {
    @GetMapping(value = "hello")
    public String hello(){
        NumberFormat
        return "hello get";

    }
}
