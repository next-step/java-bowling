package bowling.domain;

import bowling.domain.pitch.Pitch;

import java.util.Objects;

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

    public static Score strike() {
        return new Score(10, 2);
    }

    public static Score spare() {
        return new Score(10, 1);
    }

    public static Score open(int score) {
        return new Score(score, 0);
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

    public Score play(Pitch pitch) {
        return play(pitch.knockedPins());
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

    public int left() {
        return left;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
