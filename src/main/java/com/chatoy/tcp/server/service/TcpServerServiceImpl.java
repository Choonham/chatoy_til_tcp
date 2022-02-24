package com.chatoy.tcp.server.service;

import com.chatoy.aop.logger.service.LoggerService;
import com.chatoy.tcp.server.vo.ServerSideStreamVault;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TcpServerServiceImpl implements TcpServerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ArrayList<ServerSideStreamVault> socketList;
    ServerSocket serverSocket = null;
    Socket socket = null;

    @Async
    public void waitForAClient() throws Exception{
        socketList = new ArrayList<>();

        this.serverSocket = getServerSocketInstance();
        while(true) {
            logger.info("연결 요청을 기다리는 중...");
            this.socket = serverSocket.accept(); // 클라이언트의 연결 요청을 받아 Socket 객체 return. 즉, socket 에 요청이 들어와 있는 지
            // 지속적으로 확인함

            SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]"); // 시간 포맷 선언
            Date d = new Date();
            String time = f.format(d); // Date에 선언한 포맷 적용
            logger.info(time + "클라이언트(" + socket.getInetAddress() + ") 접속");

        }

    }

    public String showMessage(){

        try {
            ServerSideStreamVault.in = new DataInputStream(this.socket.getInputStream());
            ServerSideStreamVault.messageVault += ServerSideStreamVault.in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerSideStreamVault.messageVault;
    }

    private ServerSocket getServerSocketInstance() throws Exception{
        if(this.serverSocket == null) {
            this.serverSocket = new ServerSocket(7777);
        }
        return serverSocket;
    }


}

