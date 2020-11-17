package bowling.domain.state;

import bowling.domain.point.Point;

public class Strike implements State {
    public static final int GUTTER = 0;
    private static final int STRIKE = 10;

    private final Point point;

    public Strike() {
        this.point = Point.of(STRIKE);
    }

    @Override
    public State nextScore(Point point) {
        int totalPoint = this.point.getPoint() + point.getPoint();
        if (point.getPoint() == STRIKE) {
            return new Strike();
        }
        if (totalPoint - 10 == STRIKE) {
            return new Spare(point);
        }
        if (point.getPoint() == GUTTER) {
            return new Gutter();
        }
        return new Normal(point);

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

    @Override
    public int getPoint() {
        return point.getPoint();
    }
}
