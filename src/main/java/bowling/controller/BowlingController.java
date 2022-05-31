package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {
    public void run() {
        BowlingGames bowlingGames = BowlingGames.create(InputView.inputPlayer());
        OutputView.printBowlingScoreBoard(bowlingGames.getValues());

        while (bowlingGames.isNextPitchingAndIncreaseFrameNumber()) {
            bowlingGames.getValues()
                    .forEach(bowlingGame -> play(bowlingGames, bowlingGame));
        }
    }

    private void play(BowlingGames bowlingGames, BowlingGame bowlingGame) {
        while (bowlingGame.isFrameProgress(bowlingGames.getFrameNumber())) {
            bowlingGame.bowl(InputView.inputFallenPins(bowlingGame.getPlayerName()));
            OutputView.printBowlingScoreBoard(bowlingGames.getValues());
        }
    }
}
