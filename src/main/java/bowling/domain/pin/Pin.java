package bowling.domain.pin;

import java.util.Objects;

public class Pin {
    public static final int MAXIMUM_PIN_NUMBER = 10;
    public static final int MINIMUM_PIN_NUMBER = 0;

    public static final String PIN_MAX_ERROR = "10개보다 핀이 더 생길 수 없습니다.";
    public static final String PIN_MIN_ERROR = "볼링 핀은 최초 0 미만이 될 수 없습니다. ";

    private int pins;

    public Pin() {
        this.pins = 0;
    }

    public Pin(int pins) {
        this.pins = validatePin(pins);
    }

    private int validatePin(int pins) {
        if (pins > MAXIMUM_PIN_NUMBER) {
            throw new IllegalArgumentException(PIN_MAX_ERROR);
        }
        if (pins < MINIMUM_PIN_NUMBER) {
            throw new IllegalArgumentException(PIN_MIN_ERROR);
        }
        return pins;
    }

    public Boolean isStrike() {
        return pins == MAXIMUM_PIN_NUMBER;
    }

    public int getPin() {
        return pins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pin)) return false;
        Pin pin = (Pin) o;
        return pins == pin.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
