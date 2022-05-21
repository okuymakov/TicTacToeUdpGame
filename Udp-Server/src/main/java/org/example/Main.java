package org.example;

import org.example.dto.TicTacToeRequest;
import org.example.dto.TicTacToeResponse;
import org.example.game.Game;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final List<Client> clients = new ArrayList<>();
    private static final List<Game> games = new ArrayList<>();

    public static void main(String[] args) throws SocketException {
        Server server = new Server(1234);
        server.run(Main::onGet);
    }

    public static TicTacToeResponse onGet(TicTacToeRequest req, InetAddress inetAddress, int port) {
        Client client = new Client(inetAddress, port);
        TicTacToeResponse response = new TicTacToeResponse();
        boolean isNewClient = !clients.contains(client);
        if (isNewClient) {
            clients.add(client);
            if (req.isCreateNewGameRequest()) {
                Game game = new Game(req.getN(), req.getCellType());
                game.addClient(client);
                games.add(game);
                client.setGame(game);
                response.GameCreatedSuccessfully = true;
                return response;
            } else if (req.isFindNewGameRequest()) {
                Optional<Game> game = games.stream().filter(g -> !g.isGameStarted()).findAny();
                if (game.isPresent()) {
                    if (game.get().addClient(client)) {
                        client.setGame(game.get());
                        response.GameStartedSuccessfully = true;
                        return response;
                    }
                }
                response.GameStartedSuccessfully = false;
                return response;
            }
        } else {
            if (req.isMakeMoveRequest()) {
                client.getGame().Move(req.getX(),req.getY());
            } else if (req.isStopGameRequest()) {

            }
        }
        return null;
    }

}
