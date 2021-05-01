package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Optional;

import bowling.domain.state.BowlingPin;
import bowling.domain.state.State;
import bowling.domain.state.States;
import bowling.domain.state.progress.Ready;

public abstract class Frame {
    protected static final int TOTAL_FRAME_SIZE = 10;
    protected static final int NORMAL_STATE_SIZE = 2;

    protected final int frameNumber;
    protected final States states;

    Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.states = new States(new ArrayList<>());
    }

    public State currentState() {
        if (states.isEmpty() || states.lastState().isDone()) {
            return new Ready();
        }
        return states.lastState();
    }

    public States states() {
        return states;
    }

    public boolean isFinalFrame() {
        return frameNumber == TOTAL_FRAME_SIZE;
    }

    abstract Frame bowl(BowlingPin bowlingPin);

    abstract Optional<Frame> getNext();

    public abstract boolean isDone();
}
