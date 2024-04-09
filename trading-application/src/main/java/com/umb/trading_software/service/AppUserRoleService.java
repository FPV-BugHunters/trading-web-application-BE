package com.umb.trading_software.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umb.trading_software.domain.AppUserRepository;
import com.umb.trading_software.domain.AppUserRoleRepository;
import com.umb.trading_software.domain.AppUserRole;

@Service
public class AppUserRoleService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppUserRoleRepository appUserRoleRepository;

}