package bowling.domain.score;

import bowling.domain.frame.Point;

public class Miss implements Score {

    private final Point point;

    public Miss(Point point) {
        this.point = point;
    }

    @Override
    public Score nextScore(Point point) {
        return new Open(point);
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
