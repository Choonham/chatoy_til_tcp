package com.chatoy.test;

import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpTestServer{

    ServerSocket serverSocket = null;

    public void waitForAClient() {
        try{
            serverSocket = getServerSocketInstance();

            while (true) {
                try {
                    System.out.println("연결 요청을 기다리는 중...");
                    System.out.println(serverSocket.getInetAddress());

                    Socket socket = serverSocket.accept(); // 클라이언트의 연결 요청을 받아 Socket 객체 return. 즉, socket 에 요청이 들어와 있는 지
                    // 지속적으로 확인함

                    SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]"); // 시간 포맷 선언
                    Date d = new Date();
                    String time = f.format(d); // Date에 선언한 포맷 적용
                    System.out.println(time + "클라이언트(" + socket.getInetAddress() + ") 접속");

                    OutputStream out = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeUTF("서버로부터 메시지가 전송되었습니다. ");

                    d = new Date();
                    time = f.format(d);

                    dos.close();
                    out.close();
                    socket.close();

                } catch (IOException e) {
                    System.out.println("소캣 에러: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("소캣 에러: " + e.getMessage());
        }

    }

    private ServerSocket getServerSocketInstance() throws Exception{
        if(this.serverSocket == null) {
            this.serverSocket = new ServerSocket(7777);
        }
        return serverSocket;
    }
}
