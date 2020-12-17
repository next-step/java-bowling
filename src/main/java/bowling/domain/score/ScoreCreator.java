package bowling.domain.score;

import bowling.domain.frame.Point;

public class ScoreCreator {

    private static final int STRIKE_POINT = 10;
    private static final int GUTTER_POINT = 0;

    public static Score create(Point point) {
        return getScore(point);
    }

    private static Score getScore(Point point) {
        if (isStrike(point)) {
            return new Strike();
        }

        if (isGutter(point)) {
            return new Gutter();
        }

        return new Open(point);
    }

    private static boolean isStrike(Point point) {
        return point.getPoint() == STRIKE_POINT;
    }

    private static boolean isGutter(Point point) {
        return point.getPoint() == GUTTER_POINT;
    }
}
