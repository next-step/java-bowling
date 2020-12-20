package bowling.domain;

import java.util.Random;

public class BowlingKnockDownGenerator implements BowlingGenerator {
    private static Random RANDOM = new Random();

    @Override
    public int countOfKnockDown() {
        return RANDOM.nextInt(11);
    }
}
