package bowling.domain;

import java.util.Objects;

public class Pin {

    private static final String PIN_BOWL_RANGE = "볼링 한 구당 쓰러트릴수 있는 핀의 갯수는 0 ~ 10 사이입니다.";

    private static final String BOWLING_STATUS_STRIKE = "X";
    private static final String BOWLING_STATUS_SPARE = "/";
    private static final String BOWLING_STATUS_GUTTER = "-";

    public static final int MAXIMUM_PIN_COUNT = 10;
    private static final int MINIMUM_PIN_COUNT = 0;

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
        if (isStrike()) {
            return false;
        }
        return totalFallenPins(secondPins) == MAXIMUM_PIN_COUNT;
    }

    private int totalFallenPins(Pin secondPins) {
        return pins + secondPins.count();
    }

    private String ifCountOfPinsZeroTransGutter() {
        if (pins == MINIMUM_PIN_COUNT) {
            return BOWLING_STATUS_GUTTER;
        }
        return String.valueOf(pins);
    }

    public String record() {
        if (isStrike()) {
            return BOWLING_STATUS_STRIKE;
        }
        return ifCountOfPinsZeroTransGutter();
    }

    public String record(Pin secondPin) {
        if (isSpare(secondPin)) {
            return BOWLING_STATUS_SPARE;
        }
        return String.valueOf(secondPin.count());
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
