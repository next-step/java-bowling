package bowling.domain;

import java.util.Random;

public class RandomGenerator {
    private static final Random RANDOM = new Random();
    private static final int BOUND_FIRST_POINT = 11;

    private int firstPoint;
    private int thirdPoint;

    public int getFirstPoint() {
        firstPoint = RANDOM.nextInt(BOUND_FIRST_POINT);
        return firstPoint;
    }

    public int getSecondPoint() {
        return RANDOM.nextInt(BOUND_FIRST_POINT - firstPoint);
    }

    public int getThirdPoint() {
        return RANDOM.nextInt(BOUND_FIRST_POINT);
    }

    public int getThirdPointForStrike() {
        thirdPoint = RANDOM.nextInt(BOUND_FIRST_POINT);
        return thirdPoint;
    }

    public int getFourthPointForStrike() {
        return RANDOM.nextInt(BOUND_FIRST_POINT - thirdPoint);
    }
}