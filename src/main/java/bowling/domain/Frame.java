package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Frame {

    public static final String FRAME_DELIMITER = "|";

    protected FrameNumber frameNumber;
    protected final List<Pin> pins;

    public Frame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.pins = new LinkedList<>();
    }

    public abstract Frame nextFrame();

    public abstract void pitch(int pinCount);

    public abstract Boolean isNextFrame();

    public abstract Boolean isEnd();

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    public int getNumber() {
        return frameNumber.getNumber();
    }

    public List<Pin> getPins() {
        return pins;
    }

    public int getSumPinCount() {
        return pins.stream()
                .map(Pin::getPinCount)
                .reduce(Pin.MIN_PIN_COUNT, Integer::sum);
    }

    public int getPitchCount() {
        return pins.size();
    }

    public String getPinsToString() {
        return pins.stream()
                .map(Pin::getCountToString)
                .collect(Collectors.joining(FRAME_DELIMITER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(frameNumber, frame.frameNumber) && Objects.equals(pins, frame.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, pins);
    }
}
