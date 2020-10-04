package bowling.domain.pin;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pin {
    private static final String VALID_MAX_PIN_MESSAGE = "점수는 10점을 넘을수 없습니다.";
    private static final String VALID_MIN_PIN_MESSAGE = "점수는 0점이상이어야 합니다.";
    public static final int STRIKE_PIN_COUNT = 10;
    public static final int GUTTER_PIN_COUNT = 0;
    private static final List<Pin> pins = IntStream.rangeClosed(GUTTER_PIN_COUNT, STRIKE_PIN_COUNT)
            .mapToObj(Pin::new)
            .collect(Collectors.toList());

    private final int pin;

    private Pin(int pin) {
        validatePin(pin);
        this.pin = pin;
    }

    public static Pin of(int pin) {
        validatePin(pin);
        return pins.get(pin);
    }

    public int getPin() {
        return pin;
    }

    private static void validatePin(int pin) {
        if (pin > STRIKE_PIN_COUNT) {
            throw new IllegalArgumentException(VALID_MAX_PIN_MESSAGE);
        }

        if (pin < GUTTER_PIN_COUNT) {
            throw new IllegalArgumentException(VALID_MIN_PIN_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin that = (Pin) o;
        return pin == that.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
