package com.chatoy.tcp.client.service;


import com.chatoy.tcp.server.vo.ServerSideStreamVault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TcpClientServiceImpl implements TcpClientService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    Socket socket = null;

    public String connect(String ip, String port){

        int rtnVal = 0;
        try{
            String serverIP = ip;
            int portNum = Integer.parseInt(port);
            this.socket = new Socket(serverIP, portNum);

            logger.info("서버의 연결 중.... (연결 중인 서버: " + serverIP + ":" + portNum + ")");

            rtnVal = 1;
        } catch(Exception e) {
            rtnVal = 0;
        }

        return "{\"result\":\"" + String.valueOf(rtnVal) + "\"}";
    }

    public int sendMessage(String message, String sender) {
        int rtnVal = 0;

        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]"); // 시간 포맷 선언
        Date d = new Date();
        String time = f.format(d); // Date에 선언한 포맷 적용

        try{
            ServerSideStreamVault.out = new DataOutputStream(this.socket.getOutputStream());
            ServerSideStreamVault.out.writeUTF(sender+"(" + time + "): " + message + "</br>");
            rtnVal = 1;
        } catch (Exception e){
            rtnVal = 0;
            e.printStackTrace();
        }
        return rtnVal;
    }
}
