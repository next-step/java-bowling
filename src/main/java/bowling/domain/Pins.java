package bowling.domain;

import bowling.exception.IllegalPinNumberException;

public class Pins {

    private static final int MAX_PIN_NUMBER = 10;
    private static final int MIN_PIN_NUMBER = 0;

    private final int pin;

    private Pins(int pin) {
        this.pin = pin;
    }

    public static Pins of(int pin) {
        validate(pin);
        return new Pins(pin);
    }

    public boolean isStrike() {
        return pin == MAX_PIN_NUMBER;
    }

    private static void validate(int pin) {
        if (pin > MAX_PIN_NUMBER || pin < MIN_PIN_NUMBER) {
            throw new IllegalPinNumberException(pin);
        }
    }
}

