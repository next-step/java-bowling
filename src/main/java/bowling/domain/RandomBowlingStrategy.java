package bowling.domain;

import java.util.Random;

public class RandomBowlingStrategy implements BowlingStrategy {
    private static final Random rand = new Random();

    @Override
    public Pin nextPin(int remain) {
        return new Pin(rand.nextInt(remain + 1));
    }
}
