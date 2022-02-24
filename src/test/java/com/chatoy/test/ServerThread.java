package com.chatoy.test;

public class ServerThread implements Runnable {

    private TcpTestServer tcpTestServer = new TcpTestServer();

    @Override
    public void run() {
        tcpTestServer.waitForAClient();
    }
}
