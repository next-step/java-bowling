package bowling_refactor.domain;

import java.util.Objects;

public class Score {

    private static final int STRIKE_LEFT_COUNT = 2;
    private static final int SPARE_LEFT_COUNT = 1;
    private static final int DEFAULT_LEFT_COUNT = 0;
    private static final int DEFAULT_SCORE = 0;
    private static final int NONE_SCORE = -1;
    private static final int LEFT_ZERO = 0;

    private int left;
    private int score;

    public Score(int left, int score) {
        this.left = left;
        this.score = score;
    }

    public static Score of(int leftScore, int score) {
        return new Score(DEFAULT_LEFT_COUNT, leftScore + score);
    }

    public static Score ofStrike(int score) {
        return new Score(STRIKE_LEFT_COUNT, score + Pin.MAX_PIN);
    }

    public static Score ofSpare(int score) {
        return new Score(SPARE_LEFT_COUNT, score + Pin.MAX_PIN);
    }

    public static Score ofNoneScore() {
        return new Score(NONE_SCORE, DEFAULT_LEFT_COUNT);
    }

    public static Score ofDefault() {
        return new Score(LEFT_ZERO, DEFAULT_SCORE);
    }

    public int getScore() {
        if (isLeft()) {
            return NONE_SCORE;
        }
        return score;
    }

    public boolean isLeft() {
        return left > LEFT_ZERO;
    }

    public Score addBonus(Pin pin) {
        if (left == LEFT_ZERO) {
            return this;
        }
        return new Score(left - 1, pin.sum(score));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return left == score1.left && score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, score);
    }
}
