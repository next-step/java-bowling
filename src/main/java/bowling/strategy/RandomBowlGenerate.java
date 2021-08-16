package bowling.strategy;

import java.util.Random;

public class RandomBowlGenerate implements BowlGenerate {

    private final Random random;

    public RandomBowlGenerate() {
        this.random = new Random();
    }

    @Override
    public int generate(int range) {
        return random.nextInt(range);
    }
}
