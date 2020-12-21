package bowling.domain;

import java.util.Objects;

public class Score {
    private final int score;
    private final int left;

    public Score() {
        score = 0;
        left = 2;
    }

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score getStrikeScore() {
        return new Score(10, 2);
    }

    public static Score getSpareScore() {
        return new Score(10, 1);
    }

    public static Score getMissScore(int pins) {
        return new Score(pins, 0);
    }

    public int getScore() {
        return score;
    }

    public Score pitch(int pins) {
        return new Score(score + pins, left - 1);
    }

    public boolean isFinished() {
        return left == 0;
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
