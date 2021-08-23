package bowling.pin;

import java.util.Objects;

public class Pin {
    public static final int MIN_COUNT_OF_PIN = 0;
    public static final int MAX_COUNT_OF_PIN = 10;

    private final int pin;

    private Pin(final int pin) {
        this.pin = pin;
    }

    public static Pin from(final int pin) {
        verifyPinCount(pin);
        return new Pin(pin);
    }

    private static void verifyPinCount(final int pin) {
        if (pin < MIN_COUNT_OF_PIN) {
            throw new IllegalArgumentException(String.format("볼링핀은 %s개 보다 적을 수 없습니다 !", MIN_COUNT_OF_PIN));
        }
        if (pin > MAX_COUNT_OF_PIN) {
            throw new IllegalArgumentException(String.format("볼링핀은 %s개 보다 적을 수 없습니다 !", MIN_COUNT_OF_PIN));
        }
    }

    public Pin willReturnSum(final Pin pin) {
        final int sumValue = this.pin + pin.pin;
        verifySumValue(sumValue);
        return new Pin(sumValue);
    }

    private void verifySumValue(final int sumValue) {
        if (sumValue > MAX_COUNT_OF_PIN) {
            throw new IllegalArgumentException(String.format("볼링핀은 %s개 보다 많을 수 없습니다 !", MAX_COUNT_OF_PIN));
        }
    }

    public boolean isMaxCount() {
        return this.pin == MAX_COUNT_OF_PIN;
    }

    public int parseInt() {
        return pin;
    }

    @Override
    public boolean equals(final Object o) {
        if (Objects.isNull(this)) {
            return true;
        }
        if (Objects.isNull(o) || getClass() != o.getClass()) {
            return false;
        }
        final Pin pin1 = (Pin) o;
        return pin == pin1.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
