package bowling.domain;

import bowling.exception.InvalidPinsException;

import java.util.Objects;

public class Pins {
    private static final int MAX = 10;
    private static final int MIN = 0;

    private final int fallenPins;

    private Pins(int fallenPins) {
        validate(fallenPins);
        this.fallenPins = fallenPins;
    }

    public static Pins of(int fallenPins) {
        return new Pins(fallenPins);
    }

    private void validate(int fallenPins) {
        if (Objects.isNull(fallenPins) || fallenPins < MIN || fallenPins > MAX) {
            throw new InvalidPinsException();
        }
    }

    public boolean isMax() {
        return fallenPins == MAX;
    }

    public boolean isMin() {
        return fallenPins == MIN;
    }


    public int getFallenPins() {
        return fallenPins;
    }

    public Pins sum(Pins otherPins) {
        return Pins.of(fallenPins + otherPins.fallenPins);
    }

    public boolean isOverMax(Pins otherPins) {
        return fallenPins + otherPins.fallenPins > MAX;
    }

    public int sumOfScore(int score) {
        return fallenPins + score;
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


}
