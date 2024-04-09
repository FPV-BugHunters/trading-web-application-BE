package com.umb.trading_software.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCredentialsDTO {
    private String username;
    private String password;
}
