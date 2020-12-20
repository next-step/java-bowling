package bowling.domain;

import java.util.Random;

public class RandomPitchStrategy implements PitchStrategy{

    private static final Random random = new Random();

    @Override
    public int makePitchResult(int leftBowlingPins) {
        return random.nextInt(leftBowlingPins + 1);
    }
}
