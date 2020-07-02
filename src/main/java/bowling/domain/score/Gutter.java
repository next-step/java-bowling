package bowling.domain.score;

import bowling.domain.point.Point;

public class Gutter implements Score {
    private static final Point POINT = Point.MIN;
    private static final ScoreType SCORE_TYPE = ScoreType.GUTTER;
    private static final int STRIKE_SCORE = 10;
    private static final int GUTTER_SCORE = 0;

    @Override
    public Score nextScore(int point) {
        if (point > STRIKE_SCORE) {
            throw new IllegalArgumentException("total Score less than 10");
        }

        if (point == STRIKE_SCORE) {
            return new Spare(point);
        }

        if (point == GUTTER_SCORE) {
            return new Gutter();
        }

        return new Normal(point);
    }

    @Override
    public int getPoint() {
        return POINT.getPoint();
    }

    @Override
    public ScoreType getScoreType() {
        return SCORE_TYPE;
    }
}
