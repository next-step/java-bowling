package bowling.domain;

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

    public static Pins spareSecondPins(Pins firstPins) {
        return new Pins(MAX_OF_PINS - firstPins.number);
    }

    public boolean isStrike() {
        return number == MAX_OF_PINS;
    }

    public boolean isSpare(Pins nextPins) {
        int sumOfPins = this.number + nextPins.number;
        return sumOfPins == MAX_OF_PINS;
    }

    public boolean isMiss() {
        return (number > MIN_OF_PINS) && (number < MAX_OF_PINS);
    }

    public boolean isGutter() {
        return this.number == MIN_OF_PINS;
    }

    public int add(Pins nextPins) {
        return this.number + nextPins.number;
    }

    public boolean exceedAllPins(Pins secondPins) {
        return this.number + secondPins.number > MAX_OF_PINS;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    public int count() {
        return number;
    }

}
