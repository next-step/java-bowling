package bowling.domain.state;

import java.util.Objects;

public class Pin {
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;

    private int fallenPins;

    public Pin(int fallenPins) {
        validPins(fallenPins);
        this.fallenPins = fallenPins;
    }

    private static void validPins(int fallenPins) {
        if (fallenPins > MAX_PINS) {
            throw new IllegalArgumentException(
                    String.format("볼링 핀은 최대 10을 넘을 수 없습니다. 현재 쓰러진 핀 수는 %d", fallenPins));
        }

        if (fallenPins < MIN_PINS) {
            throw new IllegalArgumentException(
                    String.format("볼링 핀은 최초 0 미만이 될 수 없습니다. 현재 쓰러진 핀 수는 %d", fallenPins));
        }
    }

    public int getFallenPins() {
        return fallenPins;
    }

    public boolean isStrike() {
        return fallenPins == MAX_PINS;
    }

    boolean isSpare(Pin pin) {
        return this.fallenPins + pin.getFallenPins() == MAX_PINS;
    }

    String display() {
        if (isStrike()) {
            return "X";
        }
        return String.valueOf(fallenPins);
    }

    String display(Pin pin) {
        if (isSpare(pin)) {
            return fallenPins + "|/";
        }
        return fallenPins + "|" + pin.getFallenPins();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return fallenPins == pin.fallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }
}
