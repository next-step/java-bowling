package bowling.controller;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.Roll;
import bowling.view.View;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        Game game = getGame();
        game.play();
    }

    private static Game getGame() {
        Game game = new Game();
        getPlayers().addToGame(game, Main::getRoll);
        game.registerGamePrinter(View::printGame);
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

    private static Roll getRoll(String prefix) {
        return Roll.of(View.askRoll(prefix)
                .getCountOfPins());
    }
}
