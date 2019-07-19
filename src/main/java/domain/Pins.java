package domain;

import java.util.Objects;

public class Pins {
    static final String ALERT_OUT_OF_PINS_RANGE = "쓰러진 핀의 개수는 최소 0개에서 최대 10개 입니다.";
    public static final int STRIKE_PINS = 10;
    public static final int GUTTER_PINS = 0;

    private final int fallenPins;

    private Pins(int fallenPins) {
        validationPins(fallenPins);
        this.fallenPins = fallenPins;
    }

    private void validationPins(int fallenPins) {
        if (fallenPins < GUTTER_PINS || fallenPins > STRIKE_PINS) {
            throw new IllegalArgumentException(ALERT_OUT_OF_PINS_RANGE);
        }
    }

    public static Pins from(int fallenPins) {
        return new Pins(fallenPins);
    }

    public static Pins from(Pins fallenPins) {
        return new Pins(fallenPins.fallenPins);
    }

    public boolean isStrike() {
        return this.fallenPins == STRIKE_PINS;
    }

    public boolean isSpare(Pins secondFallenPins) {
        int sumOfPins = this.fallenPins + secondFallenPins.fallenPins;
        validationPins(sumOfPins);
        return sumOfPins == STRIKE_PINS;
    }

    public boolean exceedMiss(Pins secondFallenPins) {
        return fallenPins + secondFallenPins.fallenPins >= STRIKE_PINS;
    }

    public boolean isMatch(Pins pins) {
        return this.equals(pins);
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

    @Override
    public String toString() {
        return String.valueOf(fallenPins);
    }
}
