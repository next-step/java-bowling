package bowling.strategy;

import java.util.Random;

public class RandomBowlGenerate implements BowlGenerate {

    private static final int RANDOM_NUMBER_RANGE = 10;

    private final Random random;

    public RandomBowlGenerate() {
        this.random = new Random();
    }

    @Override
    public int generate() {
        return random.nextInt(RANDOM_NUMBER_RANGE);
    }
}
