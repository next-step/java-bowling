package bowling.domain.state;

import bowling.domain.point.Point;

public class Gutter implements State {
    private final Point point;

    public Gutter() {
        point = Point.of(0);
    }

    @Override
    public State nextScore(Point point) {
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
