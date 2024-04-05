package com.umb.trading_software.DTO;

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
    private String role;
    private String firstName;
    private String lastName;
    private String email;

}

