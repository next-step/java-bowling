package bowling.domain.pin;

import java.util.Objects;
import java.util.stream.IntStream;

public class Pin {

    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Pin[] CACHE = IntStream.rangeClosed(MIN, MAX)
            .mapToObj(Pin::new)
            .toArray(Pin[]::new);
    private final int countOfFelledPin;

    private Pin(int felledPin) {
        this.countOfFelledPin = felledPin;
    }

    public static Pin of(int countOfPin) {
        try {
            return CACHE[countOfPin];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("쓰러트린 핀의 갯수는 0~10 사이여야 합니다.");
        }
    }

    public Pin add(Pin felledPin) {
        if (felledPin.countOfFelledPin == MIN) {
            return this;
        }
        return of(this.countOfFelledPin + felledPin.countOfFelledPin);
    }

    public boolean isAllFelled() {
        return countOfFelledPin == MAX;
    }

    public boolean isNotFelled() {
        return countOfFelledPin == MIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return countOfFelledPin == pin.countOfFelledPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfFelledPin);
    }

    @Override
    public String toString() {
        return String.valueOf(countOfFelledPin);
    }
}
