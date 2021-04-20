package bowling.domain;

import bowling.exception.PinsOutOfSizeException;

import java.util.Objects;

import static bowling.domain.Pin.BOWLING_PIN_MAX_SIZE;

public class Pins {

    private final Pin firstPin;
    private final Pin secondPin;

    private Pins(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Pins valueOf(int firstPin, int secondPin) {
        if (firstPin + secondPin > BOWLING_PIN_MAX_SIZE) {
            throw new PinsOutOfSizeException();
        }
        return new Pins(Pin.of(firstPin), Pin.of(secondPin));
    }

    public static Pins first(int firstPin) {
        return Pins.valueOf(firstPin, 0);
    }

    public Pins second(int secondPin) {
        return new Pins(firstPin, Pin.of(secondPin));
    }

    public int first() {
        return firstPin.value();
    }

    public int second() {
        return secondPin.value();
    }

    public int totalScore() {
        return first() + second();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Pins)) return false;
        final Pins pins = (Pins) o;
        return Objects.equals(firstPin, pins.firstPin)
                && Objects.equals(secondPin, pins.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
