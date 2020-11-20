package bowling.domain.score;

import bowling.domain.point.Point;

public class Gutter implements Score2 {
    private final Point point;

    public Gutter() {
        point = Point.of(0);
    }

    @Override
    public Score2 nextScore(Point point) {
        if (point.getPoint() == STRIKE) {
            return new Spare(point);
        }
        if (point.getPoint() == GUTTER) {
            return new Gutter();
        }
        return new Normal(point);
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

    @Override
    public int getPoint() {
        return point.getPoint();
    }
}
