package bowling.domain;

import java.util.Random;

public class BowlingPointGenerator {

    private static Random random = new Random();

    private static final int POINT_MAX_BOUND = 10;

    public static Point firstPoint() {
        return Point.of(random.nextInt(POINT_MAX_BOUND + 1));
    }

    public static Point secondPoint(int remainCount) {
        return Point.of(random.nextInt(remainCount + 1));
    }
}
