package com.chatoy.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/index.do")
    public String test() {
        return "tcpClient";
    }

    @RequestMapping("/index2.do")
    public String test2() {
        return "udpClient";
    }
}
