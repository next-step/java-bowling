package bowling.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pins {

    private static final Map<Integer, Pins> CACHE = new HashMap<>();

    static final int MIN = 0;
    static final int MAX = 10;

    static final Pins DOWN_ALL = Pins.valueOf(MAX);
    static final Pins DOWN_ZERO = Pins.valueOf(MIN);

    private int countOfDownPins;

    private Pins(int countOfDownPins) {
        this.countOfDownPins = countOfDownPins;
    }

    static Pins valueOf(int countOfDownPins) {
        if (MIN > countOfDownPins || MAX < countOfDownPins) {
            throw new IllegalArgumentException(String.format("핀은 %d~%d개를 쓰러트릴 수 있습니다.", MIN, MAX));
        }
        CACHE.computeIfAbsent(countOfDownPins, Pins::new);
        return CACHE.get(countOfDownPins);
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
        return "Pins{" +
                "countOfDownPins=" + countOfDownPins +
                '}';
    }

    public Pins sum(Pins pins) {
        return new Pins(pins.countOfDownPins + this.countOfDownPins);
    }
}