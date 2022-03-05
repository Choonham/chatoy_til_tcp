package com.chatoy.tcp.server.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ServerSideStreamVault {

    public static DataOutputStream out;
    public static DataInputStream in;
    public static String messageVault = "";
    public static ArrayList<Byte> messageVaultByte = new ArrayList<>();
    public static HashMap<String, Socket> socketMap = new HashMap<>();

}
