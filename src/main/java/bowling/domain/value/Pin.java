package bowling.domain.value;

import bowling.utils.Preconditions;

import java.util.Objects;

public class Pin {
    private static final Integer MINIMUM_PIN_SIZE = 0;
    private static final Integer MAXIMUM_PIN_SIZE = 10;

    private final int pin;

    public Pin(int pin) {
        Preconditions.checkMinimumSize(pin, MINIMUM_PIN_SIZE,
                                       String.format("쓰러트린 핀의 갯수는 %s 이상 이어야 합니다.", MINIMUM_PIN_SIZE));
        Preconditions.checkMaximumSize(pin, MAXIMUM_PIN_SIZE,
                                       String.format("쓰러트린 핀의 갯수는 %s 이하 이어야 합니다.", MAXIMUM_PIN_SIZE));

        this.pin = pin;
    }

    public static Pin from(int pin) {
        return new Pin(pin);
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
