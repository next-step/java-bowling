package bowling.domain;

import java.util.Objects;

public class Pin {

    private final String PIN_BOWL_RANGE = "볼링 한 구당 쓰러트릴수 있는 핀의 갯수는 0 ~ 10 사이입니다.";

    private final int MAXIMUM_PIN_COUNT = 10;
    private final int MINIMUM_PIN_COUNT = 0;

    private int pins;

    public Pin(int pins) {
        validationPinCount(pins);
        this.pins = pins;
    }

    public int count() {
        return pins;
    }

    private void validationPinCount(int pins) {
        if (pins > MAXIMUM_PIN_COUNT || pins < MINIMUM_PIN_COUNT) {
            throw new IllegalArgumentException(PIN_BOWL_RANGE);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return pins == pin.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
