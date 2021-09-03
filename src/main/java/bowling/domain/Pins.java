package bowling.domain;

import java.util.Objects;

public class Pins {
    private static final int MAX_PIN = 10;
    private static final int MIN_PIN = 0;

    private final int fallenPins;

    private Pins(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    public static Pins of(int fallenPins) {
        return new Pins(fallenPins);
    }

    public int getFallenPins() {
        return fallenPins;
    }

    public boolean isStrike() {
        return fallenPins == MAX_PIN;
    }

    public boolean isSpare(Pins firstPins) {
        return this.fallenPins + firstPins.fallenPins == MAX_PIN;
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
