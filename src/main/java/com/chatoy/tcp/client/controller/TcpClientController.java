package com.chatoy.tcp.client.controller;

import com.chatoy.tcp.client.service.TcpClientService;
import com.chatoy.tcp.server.service.TcpServerService;
import com.chatoy.tcp.server.vo.ServerSideStreamVault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/client")
public class TcpClientController {

    @Autowired
    TcpClientService tcpClientService;

    @RequestMapping(value = "/tcp.do", produces = "application/json; charset=utf8")
    @ResponseBody
    public String connect(String ip, String port) {
        String rtnVal = "";

        try{
            rtnVal = tcpClientService.connect(ip, port);
        } catch (Exception e){
            e.printStackTrace();
        }

        return rtnVal;
    }

    @RequestMapping(value = "/sendMessage.do", produces = "application/json; charset=utf8")
    @ResponseBody
    public String sendMessage(String message, String sender) {
        int rtnVal = 0;

        try {
            rtnVal =  tcpClientService.sendMessage(message, sender);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"result\":\"" + String.valueOf(rtnVal) + "\"}";

    }
}
