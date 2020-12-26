package bowling.domain;

import java.util.*;

public abstract class Frame {

    public static final int FIRST_PITCH_COUNT = 1;
    public static final int SECOND_PITCH_COUNT = 2;
    public static final int BONUS_PITCH_COUNT = 3;
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

    public Pin getPin(int index) {
        return pins.get(index);
    }

    public int getSumPinCount() {
        return pins.stream()
                .map(Pin::getPinCount)
                .reduce(Pin.MIN_PIN_COUNT, Integer::sum);
    }

    public int getPitchCount() {
        return pins.size();
    }

    public boolean isFinalFrame() {
        return frameNumber.equals(FrameNumber.last());
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
