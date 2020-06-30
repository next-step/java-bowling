package bowling.domain.state;

import bowling.domain.frame.Point;

public class Gutter implements State {

    private final Point point = Point.inputPoint(0);

    @Override
    public State nextScore(Point point) {
        if (point.getPoint() == STRIKE_POINT) {
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
