package bowling.domain.state;

import bowling.domain.point.Point;

public class Miss implements State {
    private static final int BONUS_POINT = 10;
    public static final String ERROR_INPUT = "잘못된 입력입니다.";
    private Point point;

    public Miss(Point point) {
        if (point.getPoint() == BONUS_POINT) {
            throw new IllegalArgumentException(ERROR_INPUT);
        }
        this.point = point;
    }

    @Override
    public State nextScore(Point point) {
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

    @Override
    public int getPoint() {
        return point.getPoint();
    }
}
