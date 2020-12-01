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
//                InputView.askSizeOfPlayers().getSize(),
                1,
                ModelMapper::getPlayer
        );
    }

    private static Player getPlayer() {
        return new Player(View.askName()
                .getName()
        );
    }

    private static Roll getRoll(String prefix) {
        return Roll.of(View.askCountOfPins(prefix)
                .getCountOfPins());
    }
}
