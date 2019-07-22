package domain;

import java.util.Objects;

public class Score {

    private int score;
    private int remainingAddition;

    private Score(int score, int remainingAddition) {
        this.score = score;
        this.remainingAddition = remainingAddition;
    }

    public static Score of(int score, int remainingAddition) {
        return new Score(score, remainingAddition);
    }

    public Score update(int fallenPins) {
        return of(score + fallenPins, remainingAddition - 1);
    }

    public int getScore() {
        return score;
    }

    public boolean isFullyCalculated() {
        return remainingAddition == 0;
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
