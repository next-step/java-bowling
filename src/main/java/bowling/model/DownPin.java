package bowling.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DownPin {

    private static final Map<Integer, DownPin> CACHE = new HashMap<>();

    public static final int MIN = 0;
    public static final int MAX = 10;

    public static final DownPin DOWN_ALL = DownPin.valueOf(MAX);
    public static final DownPin DOWN_ZERO = DownPin.valueOf(MIN);

    private int countOfDownPins;

    private DownPin(int countOfDownPins) {
        this.countOfDownPins = countOfDownPins;
    }

    public static DownPin valueOf(int countOfDownPins) {
        if (MIN > countOfDownPins || MAX < countOfDownPins) {
            throw new InvalidPinException(MIN, MAX, countOfDownPins);
        }
        return CACHE.computeIfAbsent(countOfDownPins, DownPin::new);
    }

    public DownPin sum(DownPin downPin) {
        return DownPin.valueOf(this.countOfDownPins + downPin.countOfDownPins);
    }

    public DownPin saveRemaining() {
        return DownPin.valueOf(MAX - this.countOfDownPins);
    }

    public int count() {
        return countOfDownPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownPin downPin = (DownPin) o;
        return countOfDownPins == downPin.countOfDownPins;
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