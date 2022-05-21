package org.example.game;

import org.example.Client;

public class Game {
    private boolean isGameStarted = false;
    private char curCell;
    private final char[][] board;
    private int moveCount = 0;
    private final int maxCount = 2;
    private int curCount = 0;
    private Client clientX;
    private Client clientO;

    public Game(int n, char startedCell) {
        board = new char[n][n];
        fillBoard();
        curCell = startedCell;
    }

    public GameState Move(int x, int y) {
        if (!isGameStarted) return GameState.NOT_STARTED;
        char c = curCell;
        curCell = c == TicTacToeCell.ZERO ? TicTacToeCell.CROSS : TicTacToeCell.ZERO;
        int n = board.length;
        if (board[x][y] == TicTacToeCell.BLANK) {
            board[x][y] = c;
        }
        moveCount++;

        //check col
        for (int i = 0; i < n; i++) {
            if (board[x][i] != c)
                break;
            if (i == n - 1) {
                return c == TicTacToeCell.ZERO ? GameState.WIN_O : GameState.WIN_X;
            }
        }
        //check row
        for (int i = 0; i < n; i++) {
            if (board[i][y] != c)
                break;
            if (i == n - 1) {
                return c == TicTacToeCell.ZERO ? GameState.WIN_O : GameState.WIN_X;
            }
        }
        //check diag
        if (x == y) {
            for (int i = 0; i < n; i++) {
                if (board[i][i] != c)
                    break;
                if (i == n - 1) {
                    return c == TicTacToeCell.ZERO ? GameState.WIN_O : GameState.WIN_X;
                }
            }
        }
        //check anti diag
        if (x + y == n - 1) {
            for (int i = 0; i < n; i++) {
                if (board[i][(n - 1) - i] != c)
                    break;
                if (i == n - 1) {
                    return c == TicTacToeCell.ZERO ? GameState.WIN_O : GameState.WIN_X;
                }
            }
        }

        //check draw
        if (moveCount == (Math.pow(n, 2) - 1)) {
            return GameState.DRAW;
        }

        return GameState.IS_RUNNING;
    }

    public void gameOver() {
    }

    public boolean addClient(Client client) {
        if (curCount < maxCount) {
            if (client.getCellType() == TicTacToeCell.CROSS && clientX == null) {
                clientX = client;
                curCount++;
                isGameStarted = true;
                return true;
            } else if (client.getCellType() == TicTacToeCell.ZERO && clientO == null) {
                clientO = client;
                curCount++;
                isGameStarted = true;
                return true;
            }
            if (client.getCellType() == TicTacToeCell.BLANK) {
                if (clientX == null) {
                    clientX = client;
                } else {
                    clientO = client;
                }
                curCount++;
                isGameStarted = true;
                return true;
            }
        }
        return false;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public char getCurCell() {
        return curCell;
    }
    private void fillBoard() {
        for (char[] row : board) {
            for (char c : row) {
                c = '\0';
            }
        }
    }
}

