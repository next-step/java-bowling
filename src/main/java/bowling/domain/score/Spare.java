package bowling.domain.score;

public class Spare implements Score {
    private static final int MAX_SCORE = 10;
    private static final ScoreType SCORE_TYPE = ScoreType.SPARE;

    private final int point;

    public Spare(int point) {
        this.point = point;
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
        return point;
    }

    @Override
    public ScoreType getScoreType() {
        return SCORE_TYPE;
    }
}
