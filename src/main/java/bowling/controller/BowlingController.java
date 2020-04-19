package bowling.controller;

import bowling.domain.Game;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingController {
    public static void start() {
        List<String> names = InputView.getPlayerInfo();
        Game game = new Game(names);
        while (!game.isFinished()) {
            int pinCount = InputView.getPitching(game.getCurrentPlayerName());
            game.addPin(pinCount);
            ResultView.displayGameBoard(GameAssembler.writeDto(game));
        }
    }

    private BowlingController() {
    }
}
