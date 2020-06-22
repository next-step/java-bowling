package bowling.domain.score;

import bowling.domain.point.Point;

public class Normal implements Score {
    private static final ScoreType SCORE_TYPE = ScoreType.NORMAL;
    private static final int STRIKE_SCORE = 10;
    private static final int GUTTER_SCORE = 0;

    private final Point point;

    public Normal(int point) {
        validatePoint(point);
        this.point = new Point(point);
    }

    private void validatePoint(int point) {
        if (point >= STRIKE_SCORE || point <= GUTTER_SCORE) {
            throw new IllegalArgumentException("not valid point");
        }
    }

    @Override
    public Score nextScore(int point) {
        int totalPoint = this.point.getPoint() + point;
        if (totalPoint > STRIKE_SCORE) {
            throw new IllegalArgumentException("total Score less than 10");
        }

        if (totalPoint == 10) {
            return new Spare(point);
        }

        if (totalPoint == 0) {
            return new Gutter();
        }

        return new Miss(point);
    }

    @Override
    public int getPoint() {
        return point.getPoint();
    }

    @Override
    public ScoreType getScoreType() {
        return SCORE_TYPE;
    }
}
