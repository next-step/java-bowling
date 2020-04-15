package bowling.domain.frame;

import bowling.domain.frame.state.FinalReady;
import bowling.domain.frame.state.State;
import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class FinalFrame implements Frame {
    private final FrameNumber frameNumber;
    private States states;
    private State state;

    public FinalFrame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new FinalReady();
        this.states = new States(new ArrayList<>());
    }

    @Override
    public Frame bowl(final Pins pins) {
        state.roll(pins);
        return this;
    }

    @Override
    public boolean isEnd() {
        return state.isTurnOver();
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.empty();
    }

    @Override
    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    public String getState() {
        return state.toResult();
    }

    @Override
    public States getStates() {
        return states;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
