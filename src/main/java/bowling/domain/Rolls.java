package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Rolls {
    private final List<Pin> pins;

    public Rolls(List<Pin> pins) {
        this.pins = pins;
    }

    public Rolls(Integer... score) {
        this(Arrays.stream(score)
                .map(Pin::new)
                .collect(Collectors.toList()));
    }

    public Pin sum() {
        return pins.stream()
                .reduce(Pin::add)
                .stream()
                .findFirst()
                .orElseGet(() -> new Pin(0));
    }

    public int size() {
        return pins.size();
    }

    @Override
    public String toString() {
        return "Scores{" +
                "scores=" + pins +
                '}';
    }

    public List<Pin> getScores() {
        return pins;
    }

    public void add(Pin pin) {
        this.pins.add(pin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rolls rolls1 = (Rolls) o;
        return Objects.equals(pins, rolls1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
