package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pins {
    public static final int MAX_PIN_COUNT = 10;

    private final List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public void pitch(int count) {
        if (pins.isEmpty() || getLastPin().isEnd()) {
            pins.add(new Pin(count));
            return;
        }

        pins.add(getLastPin().next(count));
    }

    private Pin getLastPin() {
        if (pins.isEmpty()) {
            throw new IllegalArgumentException("");
        }

        return pins.get(pins.size() - 1);
    }

    public boolean isEnd() {
        return getLastPin().getTotalCount() == MAX_PIN_COUNT;
    }

    public int sumAll() {
        return pins.stream()
                .mapToInt(Pin::getCount)
                .sum();
    }

    public boolean overPitching(int maxPitchCount) {
        return pins.size() >= maxPitchCount;
    }

    public List<Pin> getPins() {
        return pins;
    }

    public List<String> getScore() {
        return pins.stream()
                .map(Pin::getSymbolValue)
                .collect(Collectors.toList());
    }

    public int size() {
        return pins.size();
    }

    public boolean isEmpty() {
        return pins.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
