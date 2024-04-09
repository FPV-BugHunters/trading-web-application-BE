package com.umb.trading_software.configs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.umb.trading_software.domain.AppUser;
import com.umb.trading_software.domain.AppUserRepository;
import com.umb.trading_software.domain.AppUserRole;
import com.umb.trading_software.domain.AppUserRoleRepository;
import com.umb.trading_software.domain.CtraderAccount;
import com.umb.trading_software.domain.CtraderAccountRepository;
import com.umb.trading_software.domain.TradingAccount;
import com.umb.trading_software.domain.TradingAccountRepository;
import com.umb.trading_software.service.AppUserRoleService;

import jakarta.annotation.PostConstruct;


@Configuration
public class DataTransferConfig{
    
    @Value("${EXAMPLE_ACCESS_TOKEN}")
    private String exampleAccessToken;
    
    @Value("${EXAMPLE_CTID_TRADER_ACCOUNT_ID}")
    private Integer exampleCtid;
    
    @Value("${EXAMPLE_REFRESH_TOKEN}") 
    private String exampleRefreshToken; 


    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppUserRoleRepository appUserRoleRepository;

    @Autowired
    CtraderAccountRepository ctraderAccountRepository;
    
    @Autowired
    TradingAccountRepository tradingAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    
    
    @PostConstruct
    void transferData( ) {

        System.out.println("Transferring data");
        
        List<AppUserRole> roles = new ArrayList<>();
        
        if (appUserRoleRepository.findByName("USER").isEmpty()) {
            AppUserRole userRole = new AppUserRole();
            userRole.setName("USER");
            roles.add(userRole);
        }
        
        if (appUserRoleRepository.findByName("ADMIN").isEmpty()) {
            AppUserRole adminRole = new AppUserRole();
            adminRole.setName("ADMIN");
            roles.add(adminRole);
        }
        
        if (appUserRoleRepository.findByName("SUPER_ADMIN").isEmpty()) {
            AppUserRole superAdminRole = new AppUserRole();
            superAdminRole.setName("SUPER_ADMIN");
            roles.add(superAdminRole);
        }
        
        appUserRoleRepository.saveAll(roles);

        if(appUserRepository.findByUsername("admin").isEmpty()) {
           
            
            // Create new admin user
            AppUser user = new AppUser();
            user.setFirstName("John");
            user.setLastName("Doe");
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setEmail("user@example.com");
           
            // Add roles
            Set<AppUserRole> rolesSet = new HashSet<>();
            rolesSet.add(appUserRoleRepository.findByName("USER").get());
            rolesSet.add(appUserRoleRepository.findByName("ADMIN").get());
            user.setRoles(rolesSet);

            appUserRepository.save(user);
            CtraderAccount ctraderAccount = new CtraderAccount();
            ctraderAccount.setAppUser(user);
            ctraderAccount.setAccessToken(exampleAccessToken);
            ctraderAccount.setRefreshToken(exampleRefreshToken);


            TradingAccount tradingAccount = new TradingAccount();
            tradingAccount.setCtidTraderAccountId(exampleCtid);
            tradingAccount.setCtraderAccount(ctraderAccount);
            
            ctraderAccount.setTradingAccounts(List.of(tradingAccount));
            user.setCtraderAccounts(List.of(ctraderAccount));

            appUserRepository.save(user);
        }
        

        
        


        // ctraderAccount.setAccessToken(exampleAccessToken);
        // ctraderAccount.setRefreshToken("test");
        // ctraderAccountRepository.save(ctraderAccount);
        // if(ctraderAccountRepository.findByAccessToken(exampleAccessToken).isEmpty()) {
        // }
        

    }
    
}
