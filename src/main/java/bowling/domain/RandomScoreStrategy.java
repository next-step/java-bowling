package bowling.domain;

import bowling.engine.ScoreStrategy;

import java.util.Random;

public class RandomScoreStrategy implements ScoreStrategy {
    private static final Random RANDOM = new Random();
    private static final int TEN = 10;
    private static final int TWO = 2;

    @Override
    public int createFirst() {
        return RANDOM.nextInt(TEN) + RANDOM.nextInt(TWO);
    }

    @Override
    public int createSecond(int prior) {
        int current = createFirst();
        while (prior + current > TEN) {
            current = createFirst();
        }
        return current;
    }
}
