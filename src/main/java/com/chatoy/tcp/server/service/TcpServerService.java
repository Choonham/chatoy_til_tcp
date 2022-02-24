package com.chatoy.tcp.server.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public interface TcpServerService {
    void waitForAClient() throws Exception;

    String showMessage() throws Exception;
}
