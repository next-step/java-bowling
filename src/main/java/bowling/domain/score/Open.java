package bowling.domain.score;

import bowling.domain.frame.Point;

public class Open implements Score {

    private final Point point;

    public Open(Point point) {
        this.point = point;
    }

    @Override
    public Score nextScore(Point point) {
        int totalPoint = this.point.getPoint() + point.getPoint();

        if (totalPoint == STRIKE_POINT || totalPoint - 10 == STRIKE_POINT) {
            return new Spare(point);
        }

        if (point.getPoint() == STRIKE_POINT) {
            return new Strike();
        }

        if (point.getPoint() == GUTTER_POINT) {
            return new Gutter();
        }

        return new Miss(point);
    }

    @Override
    public String getScore() {
        return point.toString();
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
