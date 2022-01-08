package bowling.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bowling.domain.BowlingScoreBoards;
import bowling.domain.shot.ShotResult;
import bowling.engine.ScoreBoard;
import bowling.engine.ScoreBoards;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {
    public void play() {
        final int playerCount = InputView.inputPlayerCount();
        final List<String> names = Stream.generate(InputView::inputName)
                .limit(playerCount)
                .collect(Collectors.toList());

        ScoreBoards scoreBoards = BowlingScoreBoards.of(names);

        OutputView.printScoreBoards(scoreBoards);

        ScoreBoard scoreBoard = scoreBoards.first();
        while (!scoreBoards.isEnded()) {
            scoreBoard = scoreBoards.next(scoreBoard);
            int shot = InputView.inputShotResult(scoreBoard.name());

            scoreBoard.nextShot(ShotResult.of(shot));
            OutputView.printScoreBoards(scoreBoards);
            System.out.println();
        }
    }
}
