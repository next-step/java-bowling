package bowling.domain.score;

import bowling.domain.frame.Point;

public class Strike implements Score {

    private static final Point point = Point.inputPoint(STRIKE_POINT);

    @Override
    public Score nextScore(Point point) {
        int totalPoint = this.point.getPoint() + point.getPoint();

        if (point.getPoint() == STRIKE_POINT) {
            return new Strike();
        }

        if (totalPoint - 10 == STRIKE_POINT) {
            return new Spare(point);
        }

        if (point.getPoint() == GUTTER_POINT) {
            return new Gutter();
        }

        return new Normal(point);
    }

    @Override
    public int getPoint() {
        return point.getPoint();
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
