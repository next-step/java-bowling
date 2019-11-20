package com.seok2.bowling.frame.domain;

import java.util.Objects;

public class Score {

    public static final Score ZERO = Score.of(0);
    public static final Score TEN = Score.of(10);
    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        return new Score(score);
    }

    public Score add(Score augend) {
        return of(score + augend.score);
    }

    public int getScore() {
        return score;
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
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
