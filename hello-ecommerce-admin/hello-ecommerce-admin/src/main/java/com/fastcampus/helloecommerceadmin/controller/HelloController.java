package com.fastcampus.helloecommerceadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello() {
        return "/hello-page";
    }
    @GetMapping(value = "/index")
    public String index() {
        return "/index";
    }
}
