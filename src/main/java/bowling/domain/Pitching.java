package bowling.domain;

import bowling.exception.IllegalPitchingException;

public class Pitching {
    private static final int MIN_PITCHING = 0;
    public static final int MAX_PITCHING = 10;

    private final int value;

    public Pitching(int value) {
        validateValueStandardRange(value);
        this.value = value;
    }

    private void validateValueStandardRange(int value) {
        if (value < MIN_PITCHING || value > MAX_PITCHING) {
            throw new IllegalPitchingException(value);
        }
    }

    public boolean isStrike() {
        return value == MAX_PITCHING;
    }

    public boolean isSpare(Pitching otherPitching) {
        return Math.addExact(value, otherPitching.value) == MAX_PITCHING;
    }

    public boolean isGutter() {
        return value == MIN_PITCHING;
    }

    public boolean isMiss(Pitching otherPitching) {
        return Math.addExact(value, otherPitching.value) < MAX_PITCHING;
    }

    public int getValue() {
        return value;
    }

    public boolean isSum(Pitching otherPitching) {
        return Math.addExact(value, otherPitching.value) <= MAX_PITCHING;
    }

    public Score score() {
        return new Score(value);
    }

    public Score score(Pitching other) {
        return new Score(Math.addExact(value, other.value));
    }
}
