package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Frame {

    public static final int FIRST_PITCH = 1;
    public static final int SECOND_PITCH = 2;
    public static final int BONUS_PITCH = 3;

    protected List<Pin> pins;
    protected final FrameRound frameRound;

    public Frame(FrameRound frameRound) {
        this.frameRound = frameRound;
        pins = new ArrayList<>();
    }

    abstract Frame next();

    abstract public void pitch(int pinCount);

    public abstract Boolean isNextFrame();

    abstract boolean isLast();

    public int getPinsSize() {
        return pins.size();
    }

    public FrameRound getRound() {
        return frameRound;
    }

    public int getFrameRoundNumber() {
        return frameRound.getRound();
    }

    public Pin getPin(int index) {
        return pins.get(index);
    }

    public int pinCountSum() {
        return pins.stream()
                .map(Pin::getCount)
                .reduce(Pin.MIN_PIN_COUNT, Integer::sum);
    }

    public boolean isFinal() {
        return frameRound.equals(FrameRound.last());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(pins, frame.pins) && Objects.equals(frameRound, frame.frameRound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, frameRound);
    }
}
