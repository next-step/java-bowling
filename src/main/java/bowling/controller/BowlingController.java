package bowling.controller;

import bowling.domain.Game;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    public static void start() {
        String name = InputView.getName();
        Game game = new Game(name);
        while (!game.isFinished()) {
            int pinCount = InputView.getPitching(game.getCurrentFrameIndex());
            game.addPin(pinCount);
            ResultView.displayGameBoard(game);
        }
    }

    private BowlingController() {
    }
}
