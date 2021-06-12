package bowling.domain;

public class Score {
    private static final int MAX_SCORE = 30;
    private static final int MIN_SCORE = 0;
    private static final int MAX_LEFT = 3;
    private static final int MIN_LEFT = 0;

    private static final String SCORE_ERROR_MESSAGE = String.format("score는 %d 이상 %d 이하여야 합니다.", MIN_SCORE, MAX_SCORE);
    private static final String CALCULABLE_ERROR_MESSAGE = String.format("남은 횟수(left)가 %d이어야 점수를 계산할 수 있습니다.", MIN_LEFT);
    private static final String LEFT_ERROR_MESSAGE = String.format("left는 %d 이상 %d 이하여야 합니다.", MIN_LEFT, MAX_LEFT);

    private final int score;
    private final int left;

    public Score(int score, int left) {
        validateScore(score);
        validateLeft(left);

        this.score = score;
        this.left = left;
    }

    private void validateScore(int score) {
        if (score > MAX_SCORE || score < MIN_SCORE) {
            throw new IllegalArgumentException(SCORE_ERROR_MESSAGE);
        }
    }

    private void validateLeft(int left) {
        if (left > MAX_LEFT || left < MIN_LEFT) {
            throw new IllegalArgumentException(LEFT_ERROR_MESSAGE);
        }
    }

    public boolean calculable() {
        return left == MIN_LEFT;
    }

    public Score play(int knockedPinsCount) {
        return play(KnockedPins.from(knockedPinsCount));
    }

    public Score play(KnockedPins knockedPins) {
        return new Score(score + knockedPins.count(), left - 1);
    }

    public int score() {
        if (!calculable()) {
            throw new IllegalStateException(CALCULABLE_ERROR_MESSAGE);
        }
        return score;
    }
}
