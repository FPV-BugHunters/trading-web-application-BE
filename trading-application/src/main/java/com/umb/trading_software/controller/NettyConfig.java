package com.umb.trading_software.controller;


import com.spotware.connect.Config;
import com.spotware.connect.netty.AuthHelper;
import com.spotware.connect.netty.NettyClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

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
    

    @Bean
    public NettyClient nettyClient() throws InterruptedException {
        // Config config = new Config();
        System.out.println("host2: " + this.configHost);

        NettyClient nettyClient =  new NettyClient(this.configHost, this.configPort);

        AuthHelper authHelper = nettyClient.getAuthHelper();
        authHelper.authorizeOnlyOneTrader(this.configClientId, this.configClientSecret, this.configCtid, this.configAccessToken);
        
        return nettyClient;
    }
}
