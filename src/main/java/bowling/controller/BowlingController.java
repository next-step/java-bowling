package bowling.controller;

import bowling.UI.InputView;
import bowling.UI.OutputView;
import bowling.domain.BowlingGame;

public class BowlingController {

    private BowlingController() {}

    public static void play() {
        BowlingGame bowlingGame = BowlingGame.create(InputView.inputPlayerName());
        OutputView.printCurrentStatus(bowlingGame);

        while (bowlingGame.hasNextPitching()) {
            bowlingGame.bowl(InputView.inputFallenPins(bowlingGame.getCurrentFrameIndex()));
            OutputView.printCurrentStatus(bowlingGame);
        }
    }
}
