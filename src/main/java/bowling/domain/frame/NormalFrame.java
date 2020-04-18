package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.domain.state.StateHistory;

import static bowling.Constants.*;

public class NormalFrame implements Frame {

    private final StateHistory stateHistory;
    private FrameNumber frameNumber;
    private State state;

    private NormalFrame(FrameNumber frameNumber, State state, StateHistory stateHistory) {
        this.frameNumber = frameNumber;
        this.state = state;
        this.stateHistory = stateHistory;
    }

    public static Frame create(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber, new Ready(), new StateHistory());
    }

    public void play(int felledPin) {
        assertFelledPin(felledPin);
        state = state.play(new PinCount(felledPin));
        stateHistory.add(state);
    }

    @Override
    public Frame getNext() {
        if (frameNumber.isMaxNormalFrameCount()) {
            return FinalFrame.create();
        }
        return NormalFrame.create(frameNumber.getNext());
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getFrameNumber() {
        return frameNumber.getValue();
    }

    @Override
    public boolean isEndedFrame() {
        return state.isEndedState();
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    @Override
    public StateHistory getStateHistory() {
        return stateHistory;
    }

    private void assertFelledPin(int felledPin) {
        if (felledPin > MAX_FELLED_PIN_COUNT || felledPin < MIN_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }
}
