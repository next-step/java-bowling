package bowling.domain.score;

import bowling.domain.frame.Point;

public class Strike implements Score {

    private static final Point POINT = Point.inputPoint(STRIKE_POINT);

    @Override
    public Score nextScore(Point point) {
        int framePointSum = POINT.getPoint() + point.getPoint();

        if (point.getPoint() == STRIKE_POINT) {
            return new Strike();
        }

        if (framePointSum - 10 == STRIKE_POINT) {
            return new Spare(point);
        }

        if (point.getPoint() == GUTTER_POINT) {
            return new Gutter();
        }

        return new Open(point);
    }

    @Override
    public String getScore() {
        return "X";
    }

    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
