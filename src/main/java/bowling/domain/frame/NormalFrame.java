package bowling.domain.frame;

import bowling.domain.format.StateFormatter;
import bowling.domain.frame.state.Ready;
import bowling.domain.frame.state.State;
import bowling.domain.pin.Pins;

import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {
    private final FrameNumber frameNumber;
    private final Frame nextFrame;
    private State state;

    public NormalFrame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new Ready();
        this.nextFrame = next();
    }

    public static NormalFrame ofFirst() {
        return new NormalFrame(new FrameNumber(FrameNumber.MIN_NUMBER));
    }

    private Frame next() {
        final FrameNumber nextFrameNumber = frameNumber.increase();
        if (nextFrameNumber.isFinal()) {
            return new FinalFrame(nextFrameNumber);
        }
        return new NormalFrame(nextFrameNumber);
    }

    @Override
    public Frame bowl(final Pins pins) {
        state = state.roll(pins);
        if (!isEnd()) {
            return this;
        }
        return nextFrame;
    }

    @Override
    public boolean isEnd() {
        return state.isTurnOver();
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.of(nextFrame);
    }

    @Override
    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    public String getStates() {
        return StateFormatter.format(state) + Frame.SEPARATOR + nextFrame.getStates();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NormalFrame normalFrame = (NormalFrame) o;
        return Objects.equals(frameNumber, normalFrame.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
