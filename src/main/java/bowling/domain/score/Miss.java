package bowling.domain.score;

import bowling.domain.point.Point;

public class Miss implements Score {
    private final Point point;
    private static final ScoreType SCORE_TYPE = ScoreType.MISS;

    public Miss(int point) {
        this.point = new Point(point);
    }

    @Override
    public Score nextScore(int point) {
        throw new IllegalArgumentException("Miss can not create nextScore");
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
