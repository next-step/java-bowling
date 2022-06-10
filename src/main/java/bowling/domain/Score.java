package bowling.domain;

import java.util.Objects;

public class Score {
    private static final int MAX_PINS = 10;
    private final int score;
    private final int remainNumber;

    public Score(int score, int remainNumber) {
        this.score = score;
        this.remainNumber = remainNumber;
    }

    public boolean isCalculable() {
        return remainNumber == 0;
    }

    public Score bowl(int pins) {
        return new Score(this.score + pins , this.remainNumber -1);
    }

    public static Score ofStrike() {
        return new Score(MAX_PINS, 2);
    }

    public static Score ofSpare() {
        return new Score(MAX_PINS, 1);
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && remainNumber == score1.remainNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, remainNumber);
    }
}
