package com.umb.trading_software.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.umb.trading_software.domain.AppUserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<String> roles = new ArrayList<>();
    private ArrayList<Integer> tradingAccountIds = new ArrayList<>();
}

