package bowling.domain.frame;

import bowling.domain.frame.state.*;
import bowling.domain.pin.Pins;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class FinalFrame implements Frame {
    private final FrameNumber frameNumber;
    private States states;
    private Count count;

    public FinalFrame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.states = new States(new ArrayList<>());
        this.count = Count.ofFirst();
    }

    @Override
    public Frame bowl(final Pins pins) {
        State state = getCurrentState();
        states.add(state.roll(pins));
        count = count.increaseFinalFrameCount();
        return this;
    }

    @Override
    public State getCurrentState() {
        if (states.isEmpty() || states.getLast().isTurnOver()) {
            return new Ready();
        }
        return states.getLast();
    }

    @Override
    public boolean isEnd() {
        return count.isBonusCount() || isPossibleTurnOver();
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
    public States getStates() {
        return states;
    }

    private boolean isPossibleTurnOver() {
        return count.isNotBonusCount() && !hasBonusState();
    }

    private boolean hasBonusState() {
        return states.getList().stream().anyMatch(Strike.class::isInstance) ||
                states.getList().stream().anyMatch(Spare.class::isInstance);
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
