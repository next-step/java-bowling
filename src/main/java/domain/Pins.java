package domain;

import java.util.Objects;

public class Pins {
    static final String ALERT_OUT_OF_PINS_RANGE = "쓰러진 핀의 개수는 최소 0개에서 최대 10개 입니다.";

    private final int fallenPins;

    private Pins(int fallenPins) {
        validationPins(fallenPins);
        this.fallenPins = fallenPins;
    }

    private void validationPins(int fallenPins) {
        if (fallenPins < 0 || fallenPins > 10) {
            throw new IllegalArgumentException(ALERT_OUT_OF_PINS_RANGE);
        }
    }

    public static Pins from(int fallenPins) {
        return new Pins(fallenPins);
    }

    public boolean isMatch(Pins pins) {
        return this.equals(pins);
    }

    public int getFallenPins() {
        return fallenPins;
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
