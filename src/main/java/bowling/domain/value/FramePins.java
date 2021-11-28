package bowling.domain.value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FramePins {
    private final List<Pins> pins;

    public FramePins() {
        this.pins = new ArrayList<>();
    }

    public static FramePins create() {
        return new FramePins();
    }

    public void addPins(Pins pins) {
        this.pins.add(pins);
    }

    public boolean isFrameOver(int countOfPitch) {
        return pins.size() == countOfPitch;
    }

    public Pins getPins(int index) {
        return pins.get(index);
    }

    public int getTotalPins() {
        return pins.stream()
                .map(Pins::getPins)
                .reduce(0, Integer::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FramePins framePins = (FramePins) o;
        return Objects.equals(pins, framePins.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
