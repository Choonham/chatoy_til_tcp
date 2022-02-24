package com.chatoy.test;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ChatoyTest extends Config{

    private TcpTestClient tcpTestClient = new TcpTestClient();

    @BeforeEach
    public void beforeClass() {
        System.out.println("-----테스트 시작-----");
    }

    @AfterEach
    public void afterClass() {
        System.out.println("-----테스트 종료-----");
    }

    @Test
    public void startTest() throws Exception {
        Runnable serverThread = new ServerThread();

        Thread subTread = new Thread(serverThread);

        subTread.start();

        tcpTestClient.connect("59.17.200.69", "7777");
    }

}
