package com.seok2.bowling.frame.domain;

import java.util.Objects;

public class Score {

    private static final int MIN_SCORE = 0;
    private static final int ROLL_MAX_SCORE = 10;
    private int score;
    private Remaining remaining;

    public Score(int score, Remaining remaining) {
        this.score = score;
        this.remaining = remaining;
    }

    public static Score of(int score, Remaining remaining) {
        return new Score(score, remaining);
    }

    public static Score of(int score) {
        return new Score(score, Remaining.ZERO);
    }

    public static Score ofReady() {
        return new Score(MIN_SCORE, Remaining.INFINITY);
    }

    public static Score ofGutter() {
        return new Score(MIN_SCORE, Remaining.ZERO);
    }

    public static Score ofSpare() {
        return new Score(ROLL_MAX_SCORE, Remaining.SPARE);
    }

    public static Score ofStrike() {
        return new Score(ROLL_MAX_SCORE, Remaining.STRIKE);
    }

    public static Score ofPending() {
        return new Score(MIN_SCORE, Remaining.INFINITY);
    }

    public Score add(Score score) {
        return remaining.isZero() ? this : of(this.score + score.score, remaining.decrement());
    }

    public boolean isPending() {
        return !remaining.isZero();
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score)) {
            return false;
        }
        Score score1 = (Score) o;
        return getScore() == score1.getScore() &&
            Objects.equals(remaining, score1.remaining);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScore(), remaining);
    }
}
