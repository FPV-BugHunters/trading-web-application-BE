package com.umb.trading_software.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.MessageLite;
import com.spotware.connect.Config;
import com.spotware.connect.netty.NettyClient;
import com.spotware.connect.netty.handler.ProtoMessageReceiver;
import com.umb.trading_software.DTO.AppUserDTO;
import com.umb.trading_software.configs.NettyConfig;
import com.xtrader.protocol.openapi.v2.ProtoOAErrorRes;
import com.xtrader.protocol.openapi.v2.ProtoOASymbolsListReq;
import com.xtrader.protocol.openapi.v2.ProtoOASymbolsListRes;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


@RestController
public class SymbolsListController {

    private final NettyClient nettyClient;

    @Autowired
    public SymbolsListController(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }
    
    
    @Autowired
    private NettyConfig nettyConfig;
       
    @PostMapping(value = "/api/user/symbols-list", produces = "application/json")
    public ProtoOASymbolsListRes symbols(@RequestBody ProtoOASymbolsListReq protoOASymbolsListReq, HttpServletRequest request) {

        AppUserDTO user = (AppUserDTO) request.getAttribute("user");

        // Check if the user has access to the trading account
        int ctidTraderAccountId = Long.valueOf(protoOASymbolsListReq.getCtidTraderAccountId()).intValue();
        ArrayList<Integer> allowedAccounts = user.getTradingAccountIds();

        try {
            nettyConfig.authorizeAccount(allowedAccounts, ctidTraderAccountId);
            
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getReason(), e);

        } catch (InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while authorizing account", e);
        }

        
        try {

            ProtoMessageReceiver receiver = nettyClient.writeAndFlush(protoOASymbolsListReq);
            MessageLite messageLite = receiver.waitSingleResult(500L);

            if (messageLite instanceof ProtoOASymbolsListRes) {

                ProtoOASymbolsListRes response = (ProtoOASymbolsListRes) messageLite;
                return response;

            } else if (messageLite instanceof ProtoOAErrorRes) {

                ProtoOAErrorRes errorRes = (ProtoOAErrorRes) messageLite;
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorRes.toString());
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
    }
        
}
