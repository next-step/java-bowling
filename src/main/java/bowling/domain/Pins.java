package bowling.domain;

import bowling.exception.InvalidPinsException;

import java.util.Objects;

public class Pins {
    private static final int MAX_PIN = 10;
    private static final int MIN_PIN = 0;

    private final int fallenPins;

    private Pins(int fallenPins) {
        validate(fallenPins);
        this.fallenPins = fallenPins;
    }

    public static Pins of(int fallenPins) {
        return new Pins(fallenPins);
    }

    private void validate(int fallenPins) {
        if (Objects.isNull(fallenPins) || fallenPins < MIN_PIN || fallenPins > MAX_PIN) {
            throw new InvalidPinsException();
        }
    }

    public int getFallenPins() {
        return fallenPins;
    }

    public boolean isMaxPins() {
        return fallenPins == MAX_PIN;
    }

    public boolean isSumTheMaxPin(Pins firstPins) {
        return this.fallenPins + firstPins.fallenPins == MAX_PIN;
    }

    public boolean isMinPins() {
        return fallenPins == MIN_PIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return fallenPins == pins.fallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }


    public boolean isSumOverTheMaxPin(Pins firstPins) {
        return fallenPins + firstPins.fallenPins > MAX_PIN;
    }
}
