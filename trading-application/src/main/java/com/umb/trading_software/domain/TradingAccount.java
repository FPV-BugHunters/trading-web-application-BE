package com.umb.trading_software.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradingAccount {

    @Id
    @Column(nullable = false, updatable = false)
    private Integer ctidTraderAccountId;
    
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ctrader_account_id", referencedColumnName = "id")
    private CtraderAccount ctraderAccount;
    
}

