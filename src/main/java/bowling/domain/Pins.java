package bowling.domain;

import bowling.exception.BowlingException;

public class Pins {

    private static final String PINS_COUNT_RANGE = "핀은 0~10 사이여야 합니다.";
    private static final int MIN_PIN = 0;
    private static final int MAX_PIN = 10;

    private final int pins;

    public Pins() {
        this(MAX_PIN);
    }

    public Pins(int pins) {
        validatePinesCount(pins);
        this.pins = pins;
    }

    private void validatePinesCount(int pins) {
        if (pins < MIN_PIN || pins > MAX_PIN) {
            throw new BowlingException(PINS_COUNT_RANGE);
        }
    }
}
