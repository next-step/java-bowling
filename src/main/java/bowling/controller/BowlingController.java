package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    private BowlingController() {
    }

    public static void play() {
        BowlingGame bowlingGame = BowlingGame.create(InputView.inputPlayerName());
        OutputView.printCurrentStatus(bowlingGame);
        while (bowlingGame.hasNextPitching()) {
            bowlingGame.bowl(InputView.inputFallenPins(bowlingGame.getCurrentFrameIndex()));
            OutputView.printCurrentStatus(bowlingGame);
        }
    }
}
