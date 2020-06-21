package bowling.domain.score;

public class Strike implements Score {
    private static final int SCORE = 10;
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
        return SCORE;
    }

    @Override
    public ScoreType getScoreType() {
        return SCORE_TYPE;
    }
}
