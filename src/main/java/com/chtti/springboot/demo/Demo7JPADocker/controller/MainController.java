package com.chtti.springboot.demo.Demo7JPADocker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String main(){
        return "index";
    }

}
