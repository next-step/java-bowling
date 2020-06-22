package bowling.domain.score;

import bowling.domain.point.Point;

public class Gutter implements Score {
    private static final Point POINT = new Point(0);
    private static final ScoreType SCORE_TYPE = ScoreType.GUTTER;

    @Override
    public Score nextScore(int point) {
        if (point > 10) {
            throw new IllegalArgumentException("total Score less than 10");
        }

        if (point == 10) {
            return new Spare(point);
        }

        if (point == 0) {
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
