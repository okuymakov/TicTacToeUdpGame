package org.example;

import org.example.game.Game;

import java.net.InetAddress;
import java.util.Objects;

public class Client {
    private final InetAddress inetAddress;
    private final int port;
    private char cellType;

    private Game game;

    public Client(InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public int getPort() {
        return port;
    }

    public void setCellType(char cellType) {
        this.cellType = cellType;
    }
    public char getCellType() {
        return cellType;
    }

    public Game getGame() {return game;}

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return port == client.port && Objects.equals(inetAddress, client.inetAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inetAddress, port);
    }
}
