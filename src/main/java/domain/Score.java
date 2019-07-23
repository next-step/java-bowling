package domain;

import domain.frame.FrameResult;

import java.util.Objects;

import static domain.frame.FrameResult.UNFINISHED_SCORE;

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
