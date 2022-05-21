package org.example.dto;

import lombok.Value;

@Value
public class TicTacToeRequest {
    Boolean isCreateNewGameRequest;
    Boolean isFindNewGameRequest;
    Boolean isStopGameRequest;
    Boolean isMakeMoveRequest;

    Integer n;
    Character cellType;
    Integer x;
    Integer y;
}

