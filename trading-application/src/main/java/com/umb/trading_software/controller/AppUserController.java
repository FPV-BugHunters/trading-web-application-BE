package com.umb.trading_software.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umb.trading_software.DTO.AccountCredentialsDTO;
import com.umb.trading_software.DTO.AppUserDTO;
import com.umb.trading_software.configs.JwtProvider;
import com.umb.trading_software.domain.AppUser;
import com.umb.trading_software.service.AppUserService;


@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;
    
    @PostMapping("/api/user/register")
    public Long register(@RequestBody AppUserDTO user) {
        return appUserService.registerAppUser(user);
    }
    
    @GetMapping("/api/user/hello")
    public String hello() {
        return "Hello World";
    }

}
