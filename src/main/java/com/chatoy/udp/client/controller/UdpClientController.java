package com.chatoy.udp.client.controller;

import com.chatoy.tcp.client.service.TcpClientService;
import com.chatoy.udp.client.service.UdpClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = "/udpClient")
public class UdpClientController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UdpClientService udpClientService;

    @RequestMapping(value = "/sendMessage.do", produces = "application/json; charset=utf8")
    @ResponseBody
    public String sendMessage(String message, String ip, int port) {

        int rtnVal = 0;

        try{
            rtnVal = udpClientService.send(message, ip, port);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "{\"result\":\"" + String.valueOf(rtnVal) + "\"}";
    }
}
