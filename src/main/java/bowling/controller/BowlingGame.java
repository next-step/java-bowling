package bowling.controller;

import java.util.Random;

import bowling.domain.BowlingScoreBoard;
import bowling.domain.ShotResult;
import bowling.engine.ScoreBoard;
import bowling.view.OutputView;

public class BowlingGame {
    public void testPlay() {
        final String name = "XXX";
        ScoreBoard scoreBoard = BowlingScoreBoard.of(name);
        OutputView.printScoreBoard(scoreBoard);

        // 그냥 테스트 용
        Random random = new Random();
        while (!scoreBoard.isEnded()) {
            int first = random.nextInt(11);
            int second = random.nextInt(11 - first);

            System.out.println(scoreBoard.current());
            scoreBoard.nextShot(ShotResult.of(first));
            OutputView.printScoreBoard(scoreBoard);
            System.out.println();
            if (first != 10 && !scoreBoard.isEnded()) {
                System.out.println(scoreBoard.current());
                scoreBoard.nextShot(ShotResult.of(second));
                OutputView.printScoreBoard(scoreBoard);
                System.out.println();
            }
        }
    }

    public void testPerfect() {
        final String name = "XXX";
        ScoreBoard scoreBoard = BowlingScoreBoard.of(name);
        OutputView.printScoreBoard(scoreBoard);

        // 그냥 테스트 용
        while (!scoreBoard.isEnded()) {
            System.out.println(scoreBoard.current());
            scoreBoard.nextShot(ShotResult.of(10));
            OutputView.printScoreBoard(scoreBoard);
            System.out.println();
        }
    }

    public void testFullFill() {
        final String name = "XXX";
        ScoreBoard scoreBoard = BowlingScoreBoard.of(name);
        OutputView.printScoreBoard(scoreBoard);

        // 그냥 테스트 용
        while (!scoreBoard.isEnded()) {
            System.out.println(scoreBoard.current());
            scoreBoard.nextShot(ShotResult.of(5));
            OutputView.printScoreBoard(scoreBoard);
            System.out.println();
        }
    }
}
