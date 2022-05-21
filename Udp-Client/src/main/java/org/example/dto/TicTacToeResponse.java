package org.example.dto;

import lombok.Value;

@Value
public class TicTacToeResponse {
    Boolean GameCreatedSuccessfully;
    Boolean GameStartedSuccessfully;
    Boolean AllowedMakeMove;
}
