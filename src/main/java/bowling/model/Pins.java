package bowling.model;

import bowling.exception.InvalidPinsException;

public class Pins {

    private static final int MIN_OF_PINS = 0;

    private static final int MAX_OF_PINS = 10;

    private final int number;

    private Pins(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < MIN_OF_PINS || number > MAX_OF_PINS) {
            throw new InvalidPinsException(number);
        }
    }

    public static Pins create(int number) {
        return new Pins(number);
    }

}
