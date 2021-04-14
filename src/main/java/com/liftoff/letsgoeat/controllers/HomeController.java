package com.liftoff.letsgoeat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping
    @ResponseBody
    public String hello(){
        return "Hello, LetsGoEat!";
    }

}
