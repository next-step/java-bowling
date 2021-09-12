package bowling.model.frame;

import java.util.Objects;

public class Score {
    private static final int MIN = 0;
    private static final int MAX = 300;
    private static final int MAX_OF_ONE_DIGIT = 9;
    private static final int MIN_OF_TWO_DIGIT = 10;
    private static final int MAX_OF_TWO_DIGIT = 99;
    private static final int MIN_OF_THREE_DIGIT = 100;

    private int score;

    private Score(int score) {
        validateRange(score);

        this.score = score;
    }

    public static Score from(int score) {
        return new Score(score);
    }

    private void validateRange(int score) {
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException(String.format("볼링 점수는 %d점 이상 %d점 이하이어야 합니다.", MIN, MAX));
        }
    }

    public Score plusScore(int other) {
        return Score.from(score + other);
    }

    public void plus(int other) {
        score += other;
    }

    public int value() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    public boolean isOneDigit() {
        return score >= MIN && score <= MAX_OF_ONE_DIGIT;
    }

    public boolean isTwoDigit() {
        return score >= MIN_OF_TWO_DIGIT && score <= MAX_OF_TWO_DIGIT;
    }

    public boolean isThreeDigit() {
        return score >= MIN_OF_THREE_DIGIT && score <= MAX;
    }
}
