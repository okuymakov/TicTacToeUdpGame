package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.TicTacToeRequest;
import org.example.dto.TicTacToeResponse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
    private final DatagramSocket datagramSocket;
    private final InetAddress inetAddress;

    private boolean isNeedToSend = false;
    private TicTacToeRequest request;
    private ServerResponseHandler handler;
    public Client(InetAddress inetAddress) throws SocketException {
        this.datagramSocket = new DatagramSocket();
        this.inetAddress = inetAddress;
        run();
    }

    public void run() {
        while (true) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                if(isNeedToSend) {
                    String req = objectMapper.writeValueAsString(request);
                    byte[] buffer = req.getBytes();
                    DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                    datagramSocket.send(datagramPacket);
                    String dataFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                    TicTacToeResponse res = objectMapper.readValue(dataFromServer, TicTacToeResponse.class);
                    handler.onGet(res);
                    isNeedToSend = false;
                }

                byte[] buffer = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                String dataFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                TicTacToeResponse res = objectMapper.readValue(dataFromServer, TicTacToeResponse.class);

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void send(TicTacToeRequest req, ServerResponseHandler onGet) {
        isNeedToSend = true;
        request = req;
        handler = onGet;
    }
}

