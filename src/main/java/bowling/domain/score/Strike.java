package bowling.domain.score;

import bowling.domain.point.Point;

public class Strike implements Score {
    private static final Point SCORE = new Point(10);
    private static final ScoreType SCORE_TYPE = ScoreType.STRIKE;

    @Override
    public Score nextScore(int point) {
        throw new IllegalArgumentException("Strike can not create nextScore");
    }

    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public int getPoint() {
        return SCORE.getPoint();
    }

    @Override
    public ScoreType getScoreType() {
        return SCORE_TYPE;
    }
}
