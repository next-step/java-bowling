package bowling.domain;

import java.util.Objects;

public class Score {
    private static final int STRIKE_LEFT_COUNT = 2;
    private static final int SPARE_LEFT_COUNT = 1;
    private static final int DEFAULT_LEFT_COUNT = 0;
    private static final int DEFAULT_SCORE = 0;
    private static final int NONE_SCORE = -1;
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score of(int totalScore, int currentScore) {
        return new Score(totalScore + currentScore, DEFAULT_LEFT_COUNT);
    }

    public static Score ofStrike(int totalScore) {
        return new Score(totalScore + Pin.ALL_PIN_COUNT, STRIKE_LEFT_COUNT);
    }

    public static Score ofSpare(int totalScore) {
        return new Score(totalScore + Pin.ALL_PIN_COUNT, SPARE_LEFT_COUNT);
    }

    public static Score ofNoneScore() {
        return new Score(NONE_SCORE, DEFAULT_LEFT_COUNT);
    }

    public static Score ofDefaultScore() {
        return new Score(DEFAULT_SCORE, DEFAULT_LEFT_COUNT);
    }

    public int getScore() {
        if (left > 0) {
            return NONE_SCORE;
        }
        return score;
    }

    public boolean isLeft() {
        return left > 0;
    }

    public boolean isNotLeft() {
        return !isLeft();
    }

    public Score addBonus(int pin) {
        if (left == 0) {
            return this;
        }
        return new Score(score + pin, left - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
