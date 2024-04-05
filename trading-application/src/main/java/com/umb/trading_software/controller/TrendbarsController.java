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
import com.xtrader.protocol.openapi.v2.ProtoOAErrorRes;
import com.xtrader.protocol.openapi.v2.ProtoOAGetTrendbarsReq;
import com.xtrader.protocol.openapi.v2.ProtoOAGetTrendbarsRes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


@RestController
public class TrendbarsController {

    private final NettyClient nettyClient;

    @Autowired
    public TrendbarsController(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }
       
    @PostMapping(value = "/api/trendbars", produces = "application/json")
    public ProtoOAGetTrendbarsRes symbols(@RequestBody ProtoOAGetTrendbarsReq protoOAGetTrendbarsReq){

        try {

            ProtoMessageReceiver receiver = nettyClient.writeAndFlush(protoOAGetTrendbarsReq);
            MessageLite messageLite = receiver.waitSingleResult(2000L);

            if (messageLite instanceof ProtoOAGetTrendbarsRes) {

                ProtoOAGetTrendbarsRes response = (ProtoOAGetTrendbarsRes) messageLite;
                System.out.println("trendbars ----------------------------------");
                System.out.println(response);
                return response;

            } else if (messageLite instanceof ProtoOAErrorRes) {

                ProtoOAErrorRes errorRes = (ProtoOAErrorRes) messageLite;
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST,errorRes.toString()  );

            } else {
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST,"Error" );
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }

        throw new ResponseStatusException( HttpStatus.NOT_FOUND, "entity not found");

        
    }
        
}
