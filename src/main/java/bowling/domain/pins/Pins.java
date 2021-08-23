package bowling.domain.pins;

import bowling.exception.IllegalPinNumberException;

public class Pins {

    public static final int MAX_PIN_NUMBER = 10;
    public static final int MIN_PIN_NUMBER = 0;

    private final int pins;

    private Pins(int pins) {
        this.pins = pins;
    }

    public static Pins of(int pins) {
        validate(pins);
        return new Pins(pins);
    }

    public boolean isStrike() {
        return pins == MAX_PIN_NUMBER;
    }

    public boolean isSpare(Pins pins) {
        return getTotalPins(pins) == MAX_PIN_NUMBER;
    }

    public boolean isMiss(Pins pins) {
        int totalPins = getTotalPins(pins);
        return totalPins > MIN_PIN_NUMBER && getTotalPins(pins) < MAX_PIN_NUMBER;
    }

    public int getPins() {
        return pins;
    }

    private int getTotalPins(Pins pins) {
        int totalPins = this.pins + pins.pins;
        validate(totalPins);

        return totalPins;
    }

    private static void validate(int pin) {
        if (pin > MAX_PIN_NUMBER || pin < MIN_PIN_NUMBER) {
            throw new IllegalPinNumberException(pin);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(pins);
    }
}

