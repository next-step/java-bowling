package bowling_step3.domain;

import bowling_step3.exception.BowlingMaxCountException;

import java.util.Objects;

public class Pitch {

    public static final int BOWLING_MAX_NUMBER = 10;

    private final int knockDown;

    private Pitch(int knockDown) {
        this.knockDown = knockDown;
    }

    public static Pitch valueOf(int knockDown) {
        validate(knockDown);
        return new Pitch(knockDown);
    }

    private static void validate(int count) {
        if (count > BOWLING_MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }
    }

    public boolean isSpare(Pitch pitch) {
        return this.knockDown + pitch.getKnockDown() == BOWLING_MAX_NUMBER;
    }

    public int sum(Pitch otherPitch) {
        return knockDown + otherPitch.knockDown;
    }

    public int getKnockDown() {
        return knockDown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pin = (Pitch) o;
        return knockDown == pin.knockDown;
    }

    @Override
    public int hashCode() {
        return Objects.hash(knockDown);
    }

    @Override
    public String toString() {
        return String.valueOf(knockDown);
    }
}
