package bowling.domain.score;

import bowling.domain.point.Point;

public class Gutter implements Score {

    private final Point point = Point.inputPoint(0);
    private static final ScoreType SCORE_TYPE = ScoreType.GUTTER;

    @Override
    public Score nextScore(Point point) {
        if (point.getPoint() == 10) {
            return new Spare(point);
        }
    }

    @Override
    public ScoreType getScore() {
        return SCORE_TYPE;
    }
}
