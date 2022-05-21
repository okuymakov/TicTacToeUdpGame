package org.example;

import org.example.dto.TicTacToeResponse;

interface ServerResponseHandler {
    void onGet(TicTacToeResponse res);
}
