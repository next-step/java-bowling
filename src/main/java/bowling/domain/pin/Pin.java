package bowling.domain.pin;

import bowling.exception.PinOutOfBoundsException;

import java.util.Objects;

public final class Pin {

    public static final int MIN = 0;
    public static final int MAX = 10;

    private final int knockDownNumber;

    private Pin(final int knockDownNumber) {
        this.knockDownNumber = knockDownNumber;
    }

    public static Pin valueOf(final int knockDownNumber) {
        validateKnockDownPin(knockDownNumber);
        return new Pin(knockDownNumber);
    }

    private static void validateKnockDownPin(final int knockDownNumber) {
        if (knockDownNumber < MIN || knockDownNumber > MAX) {
            throw new PinOutOfBoundsException();
        }
    }

    public boolean isMinimum() {
        return knockDownNumber == MIN;
    }

    public boolean isMaximum() {
        return knockDownNumber == MAX;
    }

    public int getKnockDownNumber() {
        return knockDownNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return knockDownNumber == pin.knockDownNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(knockDownNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(knockDownNumber);
    }
}
