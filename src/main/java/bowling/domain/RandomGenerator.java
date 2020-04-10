package bowling.domain;

import java.util.Random;

public class RandomGenerator {
    private static final Random RANDOM = new Random();
    private static final int BOUND_FIRST_POINT = 11;

    private static int firstPoint;
    private static int boundForSecond = firstPoint + 1;

    public int getFirstPoint() {
        firstPoint = RANDOM.nextInt(BOUND_FIRST_POINT);
        return firstPoint;
    }

    public int getSecondPoint() {
        return RANDOM.nextInt(boundForSecond);
    }

    public static int getThirdPoint(){
        return RANDOM.nextInt(BOUND_FIRST_POINT);
    }
}
