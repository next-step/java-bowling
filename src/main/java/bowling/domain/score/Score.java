package bowling.domain.score;

public class Score {
    private static final int SCORE_LIMIT = 30;

    protected final int score;

    protected Score(int score) {
        validate(score);

        this.score = score;
    }

    public static Score from(int score) {
        return new Score(score);
    }

    private void validate(int score) {
        if (score > SCORE_LIMIT) {
            throw new IllegalArgumentException("Score can't over " + SCORE_LIMIT);
        }
    }

    public boolean isCalculable() {
        return false;
    }

    public Score add(Score anotherScore) {
        return new Score(score + anotherScore.score);
    }
}
