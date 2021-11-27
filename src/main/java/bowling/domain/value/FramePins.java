package bowling.domain.value;

import bowling.domain.type.BowlingRule;

import java.util.ArrayList;
import java.util.List;

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

    public BowlingRule matchForNormalFrame(int pins, boolean firstPitch) {
        return BowlingRule.convertForNormalFrame(pins, firstPitch);
    }

    public BowlingRule matchForFinalFrame(int pins, boolean secondPitch, boolean totalPitch) {
        return BowlingRule.convertForFinalFrame(pins, secondPitch, totalPitch);
    }
}
