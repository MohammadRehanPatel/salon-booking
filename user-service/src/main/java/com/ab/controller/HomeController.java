package com.ab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping
    public  String homeControllerHandler(){
        return "User microservice for Booking System";
    }

}
