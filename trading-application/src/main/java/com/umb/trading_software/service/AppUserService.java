package com.umb.trading_software.service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.umb.trading_software.DTO.AppUserDTO;
import com.umb.trading_software.controller.CtraderAccountController;
import com.umb.trading_software.controller.TradingAccountController;
import com.umb.trading_software.domain.AppUser;
import com.umb.trading_software.domain.AppUserRepository;
import com.umb.trading_software.domain.AppUserRole;
import com.umb.trading_software.domain.AppUserRoleRepository;
import com.umb.trading_software.domain.CtraderAccountRepository;
import com.umb.trading_software.domain.TradingAccountRepository;

@Service
public class AppUserService {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    CtraderAccountRepository ctraderAccountRepository;

    @Autowired
    TradingAccountRepository tradingAccountRepository; 
    
    
    @Autowired
    AppUserRoleRepository appUserRoleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    public long registerAppUser(AppUserDTO user) {
        AppUser appUser = new AppUser();
        appUser.setUsername(user.getUsername());
        appUser.setPassword(passwordEncoder.encode(user.getPassword())); // encode the password

        Set<AppUserRole> roles = new HashSet<>();

        roles.add(appUserRoleRepository.findByName("USER").get());
        
        appUser.setRoles(roles);

        appUser.setFirstName(user.getFirstName());
        appUser.setLastName(user.getLastName());
        appUser.setEmail(user.getEmail());

        appUserRepository.save(appUser);
        return appUser.getId();
    }
    










    
}
