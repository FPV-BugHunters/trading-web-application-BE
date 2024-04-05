package com.umb.trading_software.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {
    
    @GetMapping("/api3/app-users")
    public String hello() {
        return "Hello, App User2 !";    
    }

}
