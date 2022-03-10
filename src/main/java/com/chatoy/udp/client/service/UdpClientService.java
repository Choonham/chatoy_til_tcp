package com.chatoy.udp.client.service;

public interface UdpClientService {
    int send(String message, String ip, int port) throws Exception;
}
