package com.umb.trading_software.controller;


import com.spotware.connect.Config;
import com.spotware.connect.netty.AuthHelper;
import com.spotware.connect.netty.NettyClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class NettyConfig {

    @Bean
    public NettyClient nettyClient() throws InterruptedException {
        Config config = new Config();

        NettyClient nettyClient =  new NettyClient(config.getHost(), config.getPort());

        AuthHelper authHelper = nettyClient.getAuthHelper();
        Long ctidTraderAccountId = config.getCtid();
        authHelper.authorizeOnlyOneTrader(config.getClientId(), config.getClientSecret(), ctidTraderAccountId, config.getAccessToken());
        
        return nettyClient;
    }
}
