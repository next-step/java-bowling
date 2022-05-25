package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {
    public void run() {
        Player player = new Player(InputView.inputPlayerName());
        BowlingGame bowlingGame = new BowlingGame(player);
        OutputView.printBowlingScoreBoard(bowlingGame);

        while (bowlingGame.isNextPitching()) {
            bowlingGame.bowl(InputView.inputFallenPins(bowlingGame.getCurrentFrameNumber()));
            OutputView.printBowlingScoreBoard(bowlingGame);
        }
    }
}
