package bowling.domain;

import java.util.Objects;

public class Pin {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private int falledPins;

    public Pin(int falledPins) {
        validatePins(falledPins);
        this.falledPins = falledPins;
    }

    public void validatePins(int falledPins) {
        if (falledPins > MAX_PINS) {
            throw new IllegalArgumentException("볼링핀은 최대 10개를 넘을 수 없습니다.");
        }

        if (falledPins < MIN_PINS) {
            throw new IllegalArgumentException("볼링 핀은 최초로 0 미만이 될 수 없습니다.");
        }
    }

    public static Pin of(int falledPins) {
        return new Pin(falledPins);
    }

    public int getFalledPins() {
        return falledPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Pin)) { return false; }
        final Pin pin = (Pin) o;
        return getFalledPins() == pin.getFalledPins();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFalledPins());
    }
}
