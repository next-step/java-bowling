package bowling.domain;

import bowling.exception.InvalidPitchValueException;

import java.util.Objects;

public class Pitch {

    public static final int MIN = 0;
    public static final int MAX = 10;

    private final int value;

    public Pitch(final int value) {
        validatePitch(value);
        this.value = value;
    }

    public int value() {
        return value;
    }

    public boolean isStrike() {
        return value == MAX;
    }

    private void validatePitch(final int pitch) {
        if (pitch < MIN || pitch > MAX) {
            throw new InvalidPitchValueException(MIN, MAX);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pitch pitch1 = (Pitch) o;
        return value == pitch1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
