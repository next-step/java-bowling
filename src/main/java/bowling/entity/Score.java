package bowling.entity;

import bowling.entity.exception.ScoreOutOfRangeException;

import java.util.Objects;

public class Score {

    private static final int ZERO = 0;
    private static final int PERFECT = 10;

    private int value;

    protected Score(int value) {
        throwIfOutOfRange(value);
        this.value = value;
    }

    private void throwIfOutOfRange(int value) {
        if (value < ZERO) {
            throw new ScoreOutOfRangeException(value);
        }
    }

    public static Score of(int value) {
        return new Score(value);
    }

    public static Score zero() {
        return new Score(ZERO);
    }

    public static Score perfect() {
        return new Score(PERFECT);
    }

    public Score plus(Score score) {
        return of(value + score.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
