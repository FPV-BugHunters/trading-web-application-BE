package com.umb.trading_software.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TradingAccount {

    @Id
    @Column(nullable = false, updatable = false)
    private Long ctidTraderAccountId;
    
	public TradingAccount() { }

    public TradingAccount(Long ctidTraderAccountId) {
        super();
        this.ctidTraderAccountId = ctidTraderAccountId;
    }
    

    public Long getCtidTraderAccountId() {
        return ctidTraderAccountId;
    }

    public void setCtidTraderAccountId(Long ctidTraderAccountId) {
        this.ctidTraderAccountId = ctidTraderAccountId;
    }


    



}

