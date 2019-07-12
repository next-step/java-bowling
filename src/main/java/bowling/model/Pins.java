package bowling.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pins {

    private static final Map<Integer, Pins> CACHE = new HashMap<>();

    public static final int MIN = 0;
    public static final int MAX = 10;

    public static final Pins DOWN_ALL = Pins.valueOf(MAX);
    public static final Pins DOWN_ZERO = Pins.valueOf(MIN);

    private int countOfDownPins;

    private Pins(int countOfDownPins) {
        this.countOfDownPins = countOfDownPins;
    }

    public static Pins valueOf(int countOfDownPins) {
        if (MIN > countOfDownPins || MAX < countOfDownPins) {
            throw new IllegalArgumentException(String.format("핀은 %d~%d개 쓰러트릴 수 있습니다.", MIN, MAX));
        }
        CACHE.computeIfAbsent(countOfDownPins, Pins::new);
        return CACHE.get(countOfDownPins);
    }

    public Pins sum(Pins pins) {
        return Pins.valueOf(this.countOfDownPins + pins.countOfDownPins);
    }

    public Pins saveRemaining() {
        return Pins.valueOf(MAX - this.countOfDownPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return countOfDownPins == pins.countOfDownPins;
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