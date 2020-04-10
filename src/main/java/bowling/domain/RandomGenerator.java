package bowling.domain;

import java.util.Random;

public class RandomGenerator {
    private static final Random RANDOM = new Random();
    private static final int BOUND_FIRST_POINT = 11;

    public static int getFirstPoint() {
        return RANDOM.nextInt(BOUND_FIRST_POINT);
    }

    public static int getSecondPoint() {
        return 0;
    }
}
