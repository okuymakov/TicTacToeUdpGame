package org.example;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("localhost");
        Client client = new Client(inetAddress);
        client.send(null,(res -> {}));
    }
}