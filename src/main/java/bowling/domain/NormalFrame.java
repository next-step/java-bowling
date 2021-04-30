package bowling.domain;

import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateReady;

import java.util.Objects;

public class NormalFrame {
    private final FrameNumber frameNumber;
    private FrameState currentState = new FrameStateReady();
    private NormalFrame next;

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

    public FrameResult result() {
        return new FrameResult(currentState.pointSymbols());
    }

    public NormalFrame roll(Pinfall pinfall) {
        currentState = currentState.roll(pinfall);
        if (isDone()) {
            next = new NormalFrame(frameNumber.increase());
            return next;
        }
        return this;
    }

    public boolean isDone() {
        return !currentState.isRollable();
    }

    public boolean hasNext() {
        return next != null;
    }

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
}
