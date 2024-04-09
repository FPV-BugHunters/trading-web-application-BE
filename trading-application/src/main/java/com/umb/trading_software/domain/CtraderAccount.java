package com.umb.trading_software.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CtraderAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String refreshToken = "";
    
    @Column(nullable = false)
    private String accessToken = "";
    
    @Column(nullable = false)
    private int expiresIn = 0;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private AppUser appUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ctraderAccount", cascade = CascadeType.ALL)
    private List<TradingAccount> tradingAccounts;


}

