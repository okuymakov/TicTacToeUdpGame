package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicTacToeRequest {
    private Boolean isCreateNewGameRequest;
    private Boolean isFindNewGameRequest;
    private Boolean isStopGameRequest;
    private Boolean isMakeMoveRequest;

    private Integer n;
    private Character cellType;
    private Integer x;
    private Integer y;

}
