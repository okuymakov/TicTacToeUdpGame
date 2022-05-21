package org.example;

import org.example.dto.TicTacToeRequest;
import org.example.dto.TicTacToeResponse;

import java.net.InetAddress;

interface RequestHandler {
    TicTacToeResponse onSend(TicTacToeRequest req, InetAddress inetAddress, int port);
}
