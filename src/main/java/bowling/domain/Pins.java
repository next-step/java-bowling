package bowling.domain;

import bowling.exception.CannotBeBiggerThanMax;
import bowling.exception.CannotBeLessThanZero;
import java.util.Objects;

public class Pins {
    private static final int MIN_PIN = 0;
    private static final int MAX_PINS = 10;
    private static final String GUTTER = "-";

    private final int falledPins;

    private Pins(int falledPins) {
        this.falledPins = falledPins;
    }

    public static Pins pitching(int falledPins) {
        validPins(falledPins);
        return new Pins(falledPins);
    }

    private static void validPins(int falledPins) {
        if (falledPins < MIN_PIN) {
            throw new CannotBeLessThanZero(falledPins);
        }

        if (falledPins > MAX_PINS) {
            throw new CannotBeBiggerThanMax(falledPins);
        }
    }

    public boolean isStrike() {
        return this.falledPins == MAX_PINS;
    }

    public int totalPins(Pins secondPins) {
        int totalPins = this.falledPins + secondPins.falledPins;
        validPins(totalPins);
        return totalPins;
    }

    public boolean isSpare(Pins secondPins) {
        return totalPins(secondPins) == MAX_PINS;
    }

    public boolean isMiss(Pins secondPins) {
        return totalPins(secondPins) < MAX_PINS;
    }

    public String display() {
        if(isStrike()) {
            return "X";
        }

        return falledPins + " | ";
    }

    public String display(Pins secondPins) {
        String result = gutterCheck(falledPins);

        if (isSpare(secondPins)) {
            return result + " | /";
        }

        return result + " | " + gutterCheck(secondPins.falledPins);
    }

    private String gutterCheck(int falledPins) {
        if (falledPins == MIN_PIN) {
            return GUTTER;
        }
        return String.valueOf(falledPins);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pins pins = (Pins) o;
        return falledPins == pins.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
