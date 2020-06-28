package bowling.domain.score;

import bowling.domain.point.Point;

public class Gutter implements Score {

    private static final ScoreType SCORE_TYPE = ScoreType.GUTTER;

    private final Point point = Point.inputPoint(0);

    @Override
    public Score nextScore(Point point) {
        if (point.getPoint() == STRIKE_POINT) {
            return new Spare(point);
        }

        if (point.getPoint() == GUTTER_POINT) {
            return new Gutter();
        }

        return new Normal(point);
    }

    @Override
    public ScoreType getScore() {
        return SCORE_TYPE;
    }

    @Override
    public Point getPoint() {
        return point;
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
