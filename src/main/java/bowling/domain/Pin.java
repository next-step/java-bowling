package bowling.domain;

import java.util.Objects;

public class Pin {

    private static final String PIN_BOWL_RANGE = "한 프레임에 쓰러트릴수 있는 핀의 총합은 0 ~ 10 사이입니다.";

    public static final int MAXIMUM_PIN_COUNT = 10;
    public static final int MINIMUM_PIN_COUNT = 0;

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

    public boolean isStrike() {
        return pins == MAXIMUM_PIN_COUNT;
    }

    public boolean isSpare(Pin secondPins) {
        validationPinCount(totalFallenPins(secondPins));
        if (isStrike()) {
            return false;
        }
        return totalFallenPins(secondPins) == MAXIMUM_PIN_COUNT;
    }

    public int totalFallenPins(Pin secondPins) {
        return pins + secondPins.pins;
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
