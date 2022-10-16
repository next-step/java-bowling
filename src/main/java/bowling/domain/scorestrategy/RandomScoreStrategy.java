package bowling.domain.scorestrategy;

import bowling.domain.Score;

import java.util.Random;

public class RandomScoreStrategy implements ScoreStrategy {

    private static final Random random = new Random();

    @Override
    public Score getScore(int bound) {
        return new Score(random.nextInt(bound+1));
    }
}
