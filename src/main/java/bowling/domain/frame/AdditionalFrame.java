package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.List;

import static bowling.Constants.*;

public class AdditionalFrame implements Frame {
    private static final int ADDITIONAL_FRAME_NO = 11;

    private State state;
    private final List<State> stateHistory = new ArrayList<>();

    private AdditionalFrame(State state) {
        this.state = state;
    }

    public static Frame create() {
        return new AdditionalFrame(new Ready());
    }

    @Override
    public void play(int felledPin) {
        assertFelledPin(felledPin);
        state = state.play(felledPin);
        stateHistory.add(state);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Frame getNext() {
        return null;
    }

    @Override
    public int getFrameNumber() {
        return ADDITIONAL_FRAME_NO;
    }

    @Override
    public boolean isEndedFrame() {
        return true;
    }

    @Override
    public boolean isLastFrame() {
        return !(getState() instanceof  Ready);
    }

    @Override
    public List<State> getStateHistory() {
        return stateHistory;
    }

    private void assertFelledPin(int felledPin) {
        if(felledPin > MAX_FELLED_PIN_COUNT || felledPin < MIN_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }
}
