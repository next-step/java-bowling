package domain;

import java.util.Objects;

import static domain.Frame.ZERO;

public class Pin {
    protected static final int MIN_PINS = 0;
    protected static final int MAX_PINS = 10;

    private int fellPins;

    public Pin(int fellPins) {
        validPins(fellPins);
        this.fellPins = fellPins;
    }

    private static void validPins(int fellPins) {
        if (fellPins > MAX_PINS) {
            throw new IllegalArgumentException(
                    String.format("볼링 핀은 최대 10을 넘을 수 없습니다. 현재 쓰러진 핀 수는 %d", fellPins));
        }

        if (fellPins < MIN_PINS) {
            throw new IllegalArgumentException(
                    String.format("볼링 핀은 최초 0 미만이 될 수 없습니다. 현재 쓰러진 핀 수는 %d", fellPins));
        }
    }

    public static Pin of(int fellPins) {
        return new Pin(fellPins);
    }

    public boolean isStrike() {
        if (fellPins == MAX_PINS) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public boolean isSpare(Pin secondPin) {
        if (isNull(secondPin)) {
            return Boolean.FALSE;
        }

        if (secondPin.fellPins != ZERO && (fellPins + secondPin.fellPins) == MAX_PINS) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public boolean isMiss(Pin secondPin) {
        if (isNull(secondPin)) {
            return Boolean.FALSE;
        }

        if ((fellPins + secondPin.fellPins) < MAX_PINS) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public int getFellPins() {
        return fellPins;
    }

    private boolean isNull(Pin pin) {
        return pin == null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return fellPins == pin.fellPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fellPins);
    }
}
