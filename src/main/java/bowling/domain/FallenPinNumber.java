package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class FallenPinNumber {

    private static final int MIN_FALLEN_PIN = 0;
    private static final int MAX_FALLEN_PIN = 10;

    private static final Map<Integer, FallenPinNumber> pins = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_FALLEN_PIN, MAX_FALLEN_PIN).forEach(pin -> pins.put(pin, new FallenPinNumber(pin)));
    }

    private int fallenPinNumber;

    private FallenPinNumber(int fallenPinNumber) {
        this.fallenPinNumber = fallenPinNumber;
    }

    public static FallenPinNumber of(int pin) {
        return Optional.ofNullable(pins.get(pin))
                .orElseThrow(() -> new IllegalArgumentException("볼링 핀의 개수를 벗어났습니다."));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FallenPinNumber that = (FallenPinNumber) o;
        return fallenPinNumber == that.fallenPinNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPinNumber);
    }
}
