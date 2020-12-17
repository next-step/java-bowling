package bowling.domain.score;

import bowling.domain.frame.Point;

public class Gutter implements Score {

    private final Point point = Point.inputPoint(0);

    @Override
    public Score nextScore(Point point) {
        if (point.getPoint() == STRIKE_POINT) {
            return new Spare(point);
        }

        if (point.getPoint() == GUTTER_POINT) {
            return new Gutter();
        }

        return new Open(point);
    }

    @Override
    public String getScore() {
        return "-";
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
