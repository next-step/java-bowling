package bowling.domain.score;

import bowling.domain.point.Point;

public class Spare implements Score {
    private static final ScoreType SCORE_TYPE = ScoreType.SPARE;

    private final Point point;

    public Spare(int point) {
        this.point = new Point(point);
    }

    @Override
    public Score nextScore(int point) {
        throw new IllegalArgumentException("Spare can not create nextScore");
    }

    @Override
    public boolean isSpare() {
        return true;
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
