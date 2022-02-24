package com.chatoy.test;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class TcpTestClient {

    public void connect(String ip, String port) throws Exception {
        String serverIP = ip;
        int portNum = Integer.parseInt(port);

        Socket socket = new Socket(serverIP, portNum);

        System.out.println("서버의 연결 중.... (연결 중인 서버: " + serverIP + ")");

        InputStream in = socket.getInputStream();
        DataInputStream dis = new DataInputStream(in);

        System.out.println("SERVER MSG: " + dis.readUTF());
        System.out.println("연결 종료");

        dis.close();
        in.close();
        socket.close();
    }

    public int sendMessage(String message) throws Exception {
        return 0;
    }
}
