package com.umb.trading_software.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CtraderAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String refreshToken;
    
    @Column(nullable = false)
    private String accessToken;
    
    @Column(nullable = false)
    private int expiresIn;

	public CtraderAccount() { }
    

    public CtraderAccount(String refreshToken, String accessToken, int expiresIn) {
        super();
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public Long getId() {
        return id;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
    
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

}

