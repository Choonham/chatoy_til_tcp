package com.chatoy.tcp.client.service;

import org.springframework.stereotype.Service;

public interface TcpClientService {
    String connect(String ip, String port) throws Exception;

    int sendMessage(String message, String sender) throws Exception;
}
