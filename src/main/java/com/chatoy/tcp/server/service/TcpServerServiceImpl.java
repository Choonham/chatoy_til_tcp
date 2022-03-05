package com.chatoy.tcp.server.service;

import com.chatoy.tcp.server.vo.ServerSideStreamVault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TcpServerServiceImpl implements TcpServerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    ServerSocket serverSocket = null;
    Socket socket = null;

    @Async
    public void waitForAClient() throws Exception{
        this.serverSocket = getServerSocketInstance();
        while(true) {
            logger.info("연결 요청을 기다리는 중...");

            this.socket = serverSocket.accept(); // 클라이언트의 연결 요청을 받아 Socket 객체 return. 즉, socket 에 요청이 들어와 있는 지
            // 지속적으로 확인함

            SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]"); // 시간 포맷 선언
            Date d = new Date();
            String time = f.format(d); // Date에 선언한 포맷 적용
            logger.info(time + "클라이언트(" + socket.getInetAddress().toString() + ") 접속");

        }

    }

    public String showMessage(){
        try {

            DataInputStream i = new DataInputStream(this.socket.getInputStream());
            // Count the total bytes
            // form the input stream
            int count = i.available();
            if(count >= 1) {
                // Create byte array
                byte[] b = new byte[count];

                // Read data into byte array
                int bytes = i.read(b);

                // Print number of bytes
                // actually read
                int index = 0;
                for (byte by : b) {
                    if (index > 1) {
                        ServerSideStreamVault.messageVaultByte.add(by);
                    }
                    index++;
                }
            }
                byte[] result = new byte[ServerSideStreamVault.messageVaultByte.size()];
                for (int j = 0; j < ServerSideStreamVault.messageVaultByte.size(); j++) {
                    result[j] = ServerSideStreamVault.messageVaultByte.get(j);
                }
                return new String(result, StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ServerSocket getServerSocketInstance() throws Exception{
        if(this.serverSocket == null) {
            this.serverSocket = new ServerSocket(7777);
        }
        return serverSocket;
    }


}

