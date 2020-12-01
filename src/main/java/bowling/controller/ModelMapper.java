package bowling.controller;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.Roll;
import bowling.view.View;

class ModelMapper {
    private ModelMapper() {}

    static Game getGame() {
        Game game = new Game();
        getPlayers().addToGame(game, ModelMapper::getRoll);
        game.registerGamePrinter(View::printGame);
        return game;
    }

    private static Players getPlayers() {
        return Players.of(
                1, // View.askSizeOfPlayers().getSize(),
                ModelMapper::getPlayer
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
