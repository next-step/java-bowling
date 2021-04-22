package bowling.domain.state;

import java.util.Objects;

public class BowlingPin {
    private static final int MAX_PIN_COUNT = 10;
    private static final int MIN_PIN_COUNT = 0;

    private final int pin;

    private BowlingPin(int pin) {
        validate(pin);
        this.pin = pin;
    }

    public static BowlingPin of(int pin) {
        return new BowlingPin(pin);
    }

    private void validate(int pin) {
        if (pin < MIN_PIN_COUNT || pin > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 볼링핀 입니다.");
        }
    }
}
