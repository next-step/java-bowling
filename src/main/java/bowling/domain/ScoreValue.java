package bowling.domain;

import java.util.Objects;

public class ScoreValue {
    private static final int MIN = 0;
    private static final int MAX = 300;
    private final int score;

    private ScoreValue(int score) {
        checkValidation(score);
        this.score = score;
    }

    private void checkValidation(int score) {
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException("볼링 점수는 0~300 까지 존재합니다.");
        }
    }

    public static ScoreValue init() {
        return new ScoreValue(MIN);
    }

    public static ScoreValue of(int score) {
        return new ScoreValue(score);
    }

    public int getScore() {
        return this.score;
    }

    public ScoreValue add(ScoreValue scoreValue) {
        return ScoreValue.of(score + scoreValue.score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreValue that = (ScoreValue) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
