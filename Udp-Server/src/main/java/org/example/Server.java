package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.TicTacToeRequest;
import org.example.dto.TicTacToeResponse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    private final DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];

    public Server(int port) throws SocketException {
        datagramSocket = new DatagramSocket(port);

    }

    public void run(RequestHandler requestHandler) {
        while (true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                ObjectMapper objectMapper = new ObjectMapper();
                TicTacToeRequest req = objectMapper.readValue(data, TicTacToeRequest.class);

                TicTacToeResponse res = requestHandler.onSend(req, inetAddress, port);
                String jsonRes = objectMapper.writeValueAsString(res);
                buffer = jsonRes.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    void receiveAndSend(RequestHandler requestHandler) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(datagramPacket);
        InetAddress inetAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
        ObjectMapper objectMapper = new ObjectMapper();
        TicTacToeRequest req = objectMapper.readValue(data, TicTacToeRequest.class);

        TicTacToeResponse res = requestHandler.onSend(req, inetAddress, port);
        String jsonRes = objectMapper.writeValueAsString(res);
        buffer = jsonRes.getBytes();
        datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
        datagramSocket.send(datagramPacket);
    }

    void send(TicTacToeResponse res, InetAddress inetAddress, int port) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRes = objectMapper.writeValueAsString(res);
        buffer = jsonRes.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
        datagramSocket.send(datagramPacket);
    }
}

