package bowling.domain;

import java.util.Objects;

public class Score {
    private static final int UN_COUNTABLE_SCORE = -1;
    private static final int NO_CHANCE = 0;

    private final int leftOpportunity;
    private final int score;

    private Score(int leftOpportunity, int score) {
        this.leftOpportunity = leftOpportunity;
        this.score = score;
    }

    public static Score unCountableScore() {
        return new Score(NO_CHANCE, UN_COUNTABLE_SCORE);
    }

    public static Score Strike(int score) {
        return new Score(2, score);
    }

    public static Score Spare(int score) {
        return new Score(1, score);
    }

    public static Score Miss(int score) {
        return new Score(0, score);
    }

    public int leftOpportunity() {
        return leftOpportunity;
    }

    public int value() {
        return score;
    }

    public boolean isOpportunityLeft() {
        return leftOpportunity > NO_CHANCE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return leftOpportunity == score1.leftOpportunity &&
                score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftOpportunity, score);
    }
}
