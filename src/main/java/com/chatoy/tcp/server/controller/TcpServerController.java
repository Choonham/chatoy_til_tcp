package com.chatoy.tcp.server.controller;

import com.chatoy.tcp.AsyncConfig;
import com.chatoy.tcp.server.service.TcpServerService;
import com.chatoy.tcp.server.vo.ServerSideStreamVault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/server")
public class TcpServerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="asyncConfig")
    AsyncConfig asyncConfig;

    @Autowired
    TcpServerService tcpServerService;

    @RequestMapping(value = "/tcp.do", produces = "application/json; charset=utf8")
    public String openServer() {
        try {
            if(asyncConfig.checkSampleTaskExecute()) {
                tcpServerService.waitForAClient();
            }else {
                logger.error("Thread 개수 초과");
            }
        } catch (Exception e) {
            logger.error("Thread Err :: " + e.getMessage());
        }
        return "openServer";
    }

    @RequestMapping(value = "/showMessage.do", produces = "application/json; charset=utf8")
    @ResponseBody
    public String showMessage() {
        String message = "";
        try {
            message = tcpServerService.showMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"message\":\"" + message + "\"}";

    }
}
