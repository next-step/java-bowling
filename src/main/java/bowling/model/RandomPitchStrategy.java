package bowling.model;

import java.util.Random;

public class RandomPitchStrategy implements PitchStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public int nextPinDownCount(int leftPinCount) {
        return RANDOM.nextInt(leftPinCount + 1);
    }
}
