package bowling.domain;

import java.util.Objects;

public class Pin {
    private int falledPins;

    public Pin(int falledPins) {
        validatePins(falledPins);
        this.falledPins = falledPins;
    }

    public void validatePins(int falledPins) {
        if (falledPins > Rule.MAX_PINS.getValue()) {
            throw new IllegalArgumentException("볼링핀은 최대 10개를 넘을 수 없습니다.");
        }

        if (falledPins < Rule.MIN_PINS.getValue()) {
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
