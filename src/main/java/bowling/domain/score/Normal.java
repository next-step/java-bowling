package bowling.domain.score;

import bowling.domain.point.Point;

public class Normal implements Score2 {
    private static final String MAX_SCORE_ERROR = "일반 게임에서 합이 10을 넘을 수 없습니다";
    private static final int MAX_SCORE = 10;
    private final Point point;

    public Normal(Point point) {
        this.point = point;
    }

    @Override
    public Score2 nextScore(Point point) {
        int totalPoint = this.point.getPoint() + point.getPoint();
        validate(totalPoint);
        if (totalPoint == STRIKE || (totalPoint - 10 == STRIKE)) {
            return new Spare(point);
        }
        if (point.getPoint() == GUTTER) {
            return new Gutter();
        }
        return new Miss(point);
    }

    private void validate(int totalPoint) {
        if(totalPoint > MAX_SCORE) {
            throw new IllegalArgumentException(MAX_SCORE_ERROR);
        }
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
