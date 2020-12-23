package bowling.domain;

import java.util.Objects;

public class Pitch {

    private static final int SCORE_CONDITION = 10;
    public static final int ZERO_CONDITION = 0;
    public static final String SCORE_LIMIT = "점수는 10점 이하입니다.";
    public static final String SCORE_OVER_ZERO = "점수는 0점 이상이어야 합니다.";

    private int score;
    private int left;

    private Pitch(int score) {
        validateScore(score);
        this.score = score;
    }

    private Pitch(int score, int left) {
        validateScore(score);
        this.score = score;
        this.left = left;
    }

    public static Pitch from(int score) {
        return new Pitch(score);
    }

    public static Pitch of(int score, int left) {
        return new Pitch(score, left);
    }

    public Pitch bowl(int score) {
        return new Pitch(this.score += score, left - 1);
    }

    private void validateScore(int score) {
        validateScoreLimit(score);
        validateOverZero(score);
    }

    private void validateScoreLimit(int score) {
        if(score > SCORE_CONDITION) {
            throw new IllegalArgumentException(SCORE_LIMIT);
        }
    }

    private void validateOverZero(int score) {
        if(score < ZERO_CONDITION) {
            throw new IllegalArgumentException(SCORE_OVER_ZERO);
        }
    }

    public int getScore() {
        return score;
    }

    public boolean isStrike() {
        return score == SCORE_CONDITION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return getScore() == pitch.getScore() &&
                left == pitch.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScore(), left);
    }

    @Override
    public String toString() {
        return "" + score;
    }
}
