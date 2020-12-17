package bowling.domain.score;

import bowling.domain.frame.Point;

public class Spare implements Score {

    private final Point point;

    public Spare(Point point) {
        this.point = point;
    }

    @Override
    public Score nextScore(Point point) {
        if (point.getPoint() == STRIKE_POINT) {
            return new Strike();
        }

        if (point.getPoint() == GUTTER_POINT) {
            return new Gutter();
        }

        return new Open(point);
    }

    @Override
    public String getScore() {
        return "/";
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return true;
    }
}
