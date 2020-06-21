package bowling.domain.score;

public class Miss implements Score {
    private final int point;
    private static final ScoreType SCORE_TYPE = ScoreType.MISS;

    public Miss(int point) {
        this.point = point;
    }

    @Override
    public Score nextScore(int point) {
        throw new IllegalArgumentException("Miss can not create nextScore");
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
