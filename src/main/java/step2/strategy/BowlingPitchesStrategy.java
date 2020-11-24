package step2.strategy;

import java.util.Random;

public class BowlingPitchesStrategy implements PitchesStrategy {
    public static final int BOWLING_MAXIMUM_RANGE = 10;
    private static final Random random = new Random();

    @Override
    public int shot(int prevPoint) {
        return random.nextInt(BOWLING_MAXIMUM_RANGE - prevPoint)+1;
    }
}
