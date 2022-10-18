package bowling.domain.scorestrategy;

import bowling.domain.Pin;

import java.util.Random;

public class RandomScoreStrategy implements ScoreStrategy {

    private static final Random random = new Random();

    @Override
    public Pin getScore(int bound) {
        return new Pin(random.nextInt(bound+1));
    }
}
