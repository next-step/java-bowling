package bowling.domain.value;

import bowling.annotations.GetterForUI;

import java.util.ArrayList;
import java.util.Collections;
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

    public int calculateTotalPins() {
        return pins.stream()
                .map(Pins::getPins)
                .reduce(0, Integer::sum);
    }

    public boolean isFirstPitchStrike() {
        if (pins.isEmpty()) {
            return false;
        }

        return pins.get(0).isStrike();
    }

    @GetterForUI
    public List<Pins> getPins() {
        return Collections.unmodifiableList(new ArrayList<>(pins));
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
