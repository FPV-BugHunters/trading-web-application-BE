package com.umb.trading_software.configs;


import com.spotware.connect.Config;
import com.spotware.connect.netty.AuthHelper;
import com.spotware.connect.netty.NettyClient;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Configuration
public class NettyConfig {


    @Value("${host}")
    private String configHost;
    
    @Value("${port}")
    private int configPort;
    
    @Value("${CTRADER_CLIENT_ID}")
    private String configClientId;

    @Value("${CTRADER_CLIENT_SECRET}")
    private String configClientSecret;

    @Value("${EXAMPLE_ACCESS_TOKEN}")
    private String configAccessToken;

    @Value("${EXAMPLE_CTID_TRADER_ACCOUNT_ID}")
    private Long configCtid;

    HashMap<Integer, Long> authorizedTradingAccounts = new HashMap<Integer, Long>();
    
    @Bean
    public NettyClient nettyClient() throws InterruptedException {

        NettyClient nettyClient =  new NettyClient(this.configHost, this.configPort);
        
        AuthHelper authHelper = nettyClient.getAuthHelper();
        authHelper.authorizeApplication(this.configClientId, this.configClientSecret);
        
        return nettyClient;
    }
    

    public void authorizeAccount(ArrayList<Integer> allowedAccountIds, Integer accountId ) throws InterruptedException, ResponseStatusException {

        if (!allowedAccountIds.contains(accountId)){
            throw new ResponseStatusException( HttpStatus.FORBIDDEN, "User does not have access to this trading account");
        } 
        
        Long exp = authorizedTradingAccounts.get(accountId);

        if (exp == null || exp < System.currentTimeMillis()) {
            System.out.println("Reauthorizing account: " + accountId);
            try {
                // Authorize the account
                AuthHelper authHelper = nettyClient().getAuthHelper();
                authHelper.authorizeAccount(this.configCtid, this.configAccessToken);
                
                authorizedTradingAccounts.put(accountId, System.currentTimeMillis() + 64000L);

            } catch (InterruptedException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while authorizing account");
            }
        }

        for (Integer key : authorizedTradingAccounts.keySet()) {
            if(authorizedTradingAccounts.get(key) < System.currentTimeMillis()) {
                authorizedTradingAccounts.remove(key);
            }
        }
        
    }

    public String getExampleAccesToken() {
        return this.configAccessToken;
    }
    
    public Long getExampleCtid() {
        return this.configCtid;
    }
}
