package bowling.controller;

import bowling.domain.BowlingScoreBoard;
import bowling.domain.ShotResult;
import bowling.engine.ScoreBoard;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {
    public void play() {
        final String name = InputView.inputName();
        ScoreBoard scoreBoard = BowlingScoreBoard.of(name);
        OutputView.printScoreBoard(scoreBoard);

        while (!scoreBoard.isEnded()) {
            int shot = InputView.inputShotResult(scoreBoard.current().toInt());

            scoreBoard.nextShot(ShotResult.of(shot));
            OutputView.printScoreBoard(scoreBoard);
            System.out.println();
        }
    }
}
