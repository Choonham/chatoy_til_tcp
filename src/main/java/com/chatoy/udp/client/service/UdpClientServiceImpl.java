package com.chatoy.udp.client.service;

import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Service
public class UdpClientServiceImpl implements UdpClientService{
    public int send(String message, String ip, int port) throws Exception {
        try{
            DatagramSocket dsoc =new DatagramSocket();

            // 받을 곳의 주소 생성
            InetAddress ia = InetAddress.getByName(ip);

            // 전송할 데이터 생성
            DatagramPacket dp =new DatagramPacket(message.getBytes(),message.getBytes().length,ia, port);

            dsoc.send(dp);

            dsoc.close();

            return 1;
        } catch(Exception e) {
            return 0;
        }

    }
}
