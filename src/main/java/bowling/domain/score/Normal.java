package bowling.domain.score;

import bowling.domain.point.Point;

public class Normal implements Score {

    private final Point point;

    public Normal(Point point) {
        this.point = point;
    }

    @Override
    public Score nextScore(Point point) {
        int totalPoint = this.point.getPoint() + point.getPoint();

        if (totalPoint == STRIKE_POINT) {
            return new Spare(point);
        }

        if (totalPoint == GUTTER_POINT) {
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
