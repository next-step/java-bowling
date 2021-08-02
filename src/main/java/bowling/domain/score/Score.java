package bowling.domain.score;

public abstract class Score {
    private static final int SCORE_LIMIT = 30;
    protected final int score;

    protected Score(int score) {
        validate(score);

        this.score = score;
    }

    private void validate(int score) {
        if (score > SCORE_LIMIT) {
            throw new IllegalArgumentException("Score can't over " + SCORE_LIMIT);
        }
    }

    public abstract boolean isCalculable();
}
