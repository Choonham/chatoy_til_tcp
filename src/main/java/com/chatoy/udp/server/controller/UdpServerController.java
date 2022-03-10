package com.chatoy.udp.server.controller;

import com.chatoy.AsyncConfig;
import com.chatoy.tcp.client.service.TcpClientService;
import com.chatoy.tcp.server.service.TcpServerService;
import com.chatoy.udp.server.service.UdpServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/udpServer")
public class UdpServerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="asyncConfig")
    AsyncConfig asyncConfig;

    @Autowired
    UdpServerService udpServerService;

    @RequestMapping(value = "open.do", produces = "application/json; charset=utf8")
    public String openServer() {
        try {
            if(asyncConfig.checkSampleTaskExecute()) {
                udpServerService.openServer();
            }else {
                logger.error("Thread 개수 초과");
            }
        } catch (Exception e) {
            logger.error("Thread Err :: " + e.getMessage());
        }
        return "openUdpServer";
    }

}
