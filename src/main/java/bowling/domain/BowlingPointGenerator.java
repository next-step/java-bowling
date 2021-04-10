package bowling.domain;

import java.util.Random;

public class BowlingPointGenerator {

    private static Random random = new Random();

    public static Point firstPoint() {
        return Point.of(random.nextInt(10));
    }

    public static Point secondPoint(int remainCount) {
        return Point.of(random.nextInt(remainCount));
    }
}
