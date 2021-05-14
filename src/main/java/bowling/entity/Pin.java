package bowling.entity;

import java.util.Objects;

public class Pin {
    public static final int MAX_PIN_COUNT = 10;
    public static final int MIN_PIN_COUNT = 0;
    public static final String SCORE_ASSOCIATION_SYMBOL = "|";
    public static final String RANGE_OVER_PIN_MESSAGE = "쓰러트릴 수 있는 핀의 범위가 아닙니다.";
    public static final String SUM_RANGE_OVER_PIN_MESSAGE = "프레임 투구의 합은 10을 넘을 수 없습니다.";

    private final int pin;

    public Pin(int pin) {
        validatePin(pin);
        this.pin = pin;
    }

    private void validatePin(int pin) {
        if (pin > MAX_PIN_COUNT || pin < MIN_PIN_COUNT) {
            throw new IllegalArgumentException(RANGE_OVER_PIN_MESSAGE);
        }
    }

    public int sum(Pin secondPin) {
        int sumPin = pin + secondPin.pin;

        validateSum(sumPin);

        return sumPin;
    }

    public Score sumScore(Score score) {
        return score.calculate(this.pin);
    }

    private void validateSum(int sumPin) {
        if (sumPin > MAX_PIN_COUNT) {
            throw new IllegalArgumentException(SUM_RANGE_OVER_PIN_MESSAGE);
        }
    }

    public boolean isTenPin() {
        return this.pin == 10;
    }

    public int pin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin1 = (Pin) o;
        return pin == pin1.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
