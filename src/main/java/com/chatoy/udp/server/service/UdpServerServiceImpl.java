package com.chatoy.udp.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Service
public class UdpServerServiceImpl implements UdpServerService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    public void openServer() {
        try{
            // 상대방이 연결할수 있도록 UDP 소켓 생성
            DatagramSocket dsoc =new DatagramSocket(6666);
            // 전송받은 데이터를 지정할 바이트 배열선언
            byte [] date =new byte[66536];

            // UDP 통신으로 전송을 받을 packet 객체생성
            DatagramPacket dp =new DatagramPacket(date, date.length);

            logger.info("데이터 수신 준비 완료....");

            while(true){
                // 데이터 전송 받기
                dsoc.receive(dp);
                // 데이터 보낸곳 확인
                logger.info(" 송신 IP : " + dp.getAddress());
                String msg = new String(dp.getData(),"UTF-8").trim();
                logger.info("보내 온 내용  : " + msg);
            }

        }catch(Exception e){
            logger.error(e.getMessage());
        }

    }
}
