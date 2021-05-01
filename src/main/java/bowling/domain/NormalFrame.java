package bowling.domain;

import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateReady;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final FrameNumber frameNumber;
    private FrameState currentState = new FrameStateReady();
    private Frame next;

    public NormalFrame(Pinfall firstPinfall, Pinfall secondPinfall) {
        this(firstPinfall);
        roll(secondPinfall);
    }

    public NormalFrame(Pinfall pinfall) {
        this();
        roll(pinfall);
    }

    public NormalFrame() {
        this(new FrameNumber(1));
    }

    public NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    public NormalFrame(FrameNumber frameNumber, Pinfall firstPinfall) {
        this.frameNumber = frameNumber;
        roll(firstPinfall);
    }

    @Override
    public FrameResult result() {
        return new FrameResult(currentState.pointSymbols());
    }

    @Override
    public Frame roll(Pinfall pinfall) {
        return roll(pinfall, new NormalFrameFactory());
    }

    @Override
    public boolean isDone() {
        return !currentState.isRollable();
    }

    public boolean hasNext() {
        return next != null;
    }

    @Override
    public FrameNumber frameNumber() {
        return frameNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }

    @Override
    public Frame roll(Pinfall pinfall, FrameFatory frameFatory) {
        currentState = currentState.roll(pinfall);
        if (isDone()) {
            next = frameFatory.frame(frameNumber.increase());
            return next;
        }
        return this;
    }
}
