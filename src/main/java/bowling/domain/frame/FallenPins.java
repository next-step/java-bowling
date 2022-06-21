package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FallenPins {
    private final List<Integer> pins;

    public FallenPins() {
        this(new ArrayList<>());
    }

    public FallenPins(List<Integer> pins) {
        this.pins = pins;
    }

    public void add(int fallenPins) {
        pins.add(fallenPins);
    }

    public int size() {
        return pins.size();
    }

    public int first() {
        return pins.get(0);
    }

    public int second() {
        return pins.get(1);
    }

    public int third() {
        return pins.get(2);
    }

    public int computeSum() {
        return pins.stream()
                .mapToInt(i -> i)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FallenPins fallenPins1 = (FallenPins) o;
        return Objects.equals(pins, fallenPins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
