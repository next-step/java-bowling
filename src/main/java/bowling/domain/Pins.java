package bowling.domain;

import bowling.exception.BowlingException;

public class Pins {

    private static final String PINS_COUNT_RANGE = "핀은 0~10 사이여야 합니다.";

    private final int pins;

    public Pins() {
        this(10);
    }

    public Pins(int pins) {
        validatePinesCount(pins);
        this.pins = pins;
    }

    private void validatePinesCount(int pins) {
        if (pins < 0 || pins > 10) {
            throw new BowlingException(PINS_COUNT_RANGE);
        }
    }
}
