package bowling.controller;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.view.View;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        Game game = getGame();
        game.play();
    }

    private static Game getGame() {
        Game game = new Game();
        getPlayers().forEach(game::addPlayer);
        return game;
    }

    private static Players getPlayers() {
        return Players.of(
                1, // View.askSizeOfPlayers().getSize(),
                Main::getPlayer
        );
    }

    private static Player getPlayer() {
        return new Player(View.askPlayer()
                .getName()
        );
    }
}
