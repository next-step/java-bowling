package bowling.step2.domain;

public class Score {
    private static final int STRIKE = 10;
    private static final int ZERO = 0;
    static final String SCORE_SIZE_EXCEPTION = "볼링점수는 0-10사이의 숫자가 입력되어야 합니다.";
    private final int score;


    public Score(int score) {
        validate(score);
        this.score = score;
    }

    private void validate(int score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException(SCORE_SIZE_EXCEPTION);
        }
    }

    public boolean isStrike() {
        return this.score == STRIKE;
    }

    public boolean isZero() {
        return this.score == ZERO;
    }

    public int score() {
        return score;
    }
}
