package bowling.controller;

import java.util.Random;

import bowling.domain.BowlingScoreBoard;
import bowling.domain.ShotResult;
import bowling.engine.ScoreBoard;

public class BowlingGame {
    public void testPlay() {
        final String name = "XXX";
        ScoreBoard scoreBoard = BowlingScoreBoard.of(name);
        System.out.println(scoreBoard.collect());

        // 그냥 테스트 용
        Random random = new Random();
        while (!scoreBoard.isEnded()) {
            System.out.println(scoreBoard.current());
            int first = random.nextInt(10);
            int second = random.nextInt(10 - first);

            scoreBoard.nextShot(ShotResult.of(first));
            scoreBoard.nextShot(ShotResult.of(second));

            System.out.println(scoreBoard.collect());
        }
    }
}
