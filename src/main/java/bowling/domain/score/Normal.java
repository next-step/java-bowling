package bowling.domain.score;

import bowling.domain.point.Point;

public class Normal implements Score {

    private static final ScoreType SCORE_TYPE = ScoreType.NORMAL;

    private final Point point;

    public Normal(Point point) {
        this.point = point;
    }

    @Override
    public Score nextScore(Point point) {
        int totalPoint = this.point.getPoint() + point.getPoint();

        if (totalPoint == STRIKE_POINT) {
            return new Spare(point);
        }

        if (totalPoint == GUTTER_POINT) {
            return new Miss();
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
}
