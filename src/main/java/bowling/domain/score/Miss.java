package bowling.domain.score;

import bowling.domain.frame.Point;

public class Miss implements Score {

    private final Point point;

    public Miss(Point point) {
        validatePoint(point);
        this.point = point;
    }

    @Override
    public Score nextScore(Point point) {
        return new Normal(point);
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

    private void validatePoint(Point point) {
        if (point.getPoint() == 10) {
            throw new IllegalArgumentException("MISS 포인트는 10이 될 수 없습니다.");
        }

        if (point.getPoint() == 0) {
            throw new IllegalArgumentException("MISS 포인트는 0이 될 수 없습니다.");
        }
    }
}
