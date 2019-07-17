package bowling.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pin {

    private static final Map<Integer, Pin> CACHE = new HashMap<>();

    public static final int MIN = 0;
    public static final int MAX = 10;

    public static final Pin DOWN_ALL = Pin.valueOf(MAX);
    public static final Pin DOWN_ZERO = Pin.valueOf(MIN);

    private int countOfDownPins;

    private Pin(int countOfDownPins) {
        this.countOfDownPins = countOfDownPins;
    }

    public static Pin valueOf(int countOfDownPins) {
        if (MIN > countOfDownPins || MAX < countOfDownPins) {
            throw new InvalidPinsException(MIN, MAX, countOfDownPins);
        }
        CACHE.computeIfAbsent(countOfDownPins, Pin::new);
        return CACHE.get(countOfDownPins);
    }

    public Pin sum(Pin pin) {
        return Pin.valueOf(this.countOfDownPins + pin.countOfDownPins);
    }

    public Pin saveRemaining() {
        return Pin.valueOf(MAX - this.countOfDownPins);
    }

    public int count() {
        return countOfDownPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return countOfDownPins == pin.countOfDownPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfDownPins);
    }

    @Override
    public String toString() {
        return String.valueOf(countOfDownPins);
    }
}