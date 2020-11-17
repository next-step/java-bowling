package bowling.domain.score;

import bowling.domain.point.Point;

public class ScoreGenerator {

    private static final int STRIKE = 10;
    private static final int GUTTER = 0;

    public static State of(Point point) {
        return getScore(point);
    }

    private static State getScore(Point point) {
        if (isStrike(point)) {
            return new Strike();
        }
        if (isGutter(point)) {
            return new Gutter();
        }
        return new Normal(point);
    }

    private static boolean isStrike(Point point) {
        return point.getPoint() == STRIKE;
    }

    private static boolean isGutter(Point point) {
        return point.getPoint() == GUTTER;
    }


}
