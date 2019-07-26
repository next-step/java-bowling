package domain;

import java.util.Objects;

import static domain.Pins.STRIKE_PINS;

public class Score {
    private static final int DEFAULT_SCORE = 0;
    public static final int UNFINISHED_SCORE = -1;

    private int score;
    private int remainingAddition;

    private Score(int score, int remainingAddition) {
        this.score = score;
        this.remainingAddition = remainingAddition;
    }

    public static Score of(int score, int remainingAddition) {
        return new Score(score, remainingAddition);
    }

    public static Score ofDefault() {
        return new Score(DEFAULT_SCORE, 0);
    }

    public static Score ofUnfinished() {
        return new Score(UNFINISHED_SCORE, 0);
    }

    public static Score ofMiss(int sumOfPins) {
        return new Score(sumOfPins, 0);
    }

    public static Score ofSpare() {
        return new Score(STRIKE_PINS, 1);
    }

    public static Score ofStrike() {
        return new Score(STRIKE_PINS, 2);
    }

    public Score update(int newScore) {
        if (isFullyCalculated() || isUnfinishedScore(newScore)) {
            return this;
        }
        return of(score + newScore, remainingAddition - 1);
    }

    public boolean isFullyCalculated() {
        return remainingAddition == 0;
    }

    private boolean isUnfinishedScore(int newScore) {
        return newScore == UNFINISHED_SCORE;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                remainingAddition == score1.remainingAddition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, remainingAddition);
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", remainingAddition=" + remainingAddition +
                '}';
    }
}
