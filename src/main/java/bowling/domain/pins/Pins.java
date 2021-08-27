package bowling.domain.pins;

import bowling.domain.score.Score;
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

    public int getPins() {
        return pins;
    }

    public int getTotalPins(Pins pins) {
        int totalPins = this.pins + pins.pins;
        validate(totalPins);

        return totalPins;
    }

    public Score getScore(Score score) {
        return score.bowl(pins);
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

