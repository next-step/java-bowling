package bowling.domain.frame;

import bowling.domain.state.*;

import java.util.ArrayList;
import java.util.List;

import static bowling.Constants.*;

public class FinalFrame<state> implements Frame {
    private static final int FINAL_FRAME_NO = 10;

    private State state;
    private final List<State> stateHistory = new ArrayList<>();

    private FinalFrame(State state) {
        this.state = state;
    }

    public static Frame create() {
        return new FinalFrame(new Ready());
    }

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
        return FINAL_FRAME_NO;
    }

    /**
     * 첫번째 시도라면 firstState가 모두 null이다.
     * 두번째 시도라면 secondState만 null이다.
     * 세번째 시도라면 두 값 모두 not null이다.
     */
    @Override
    public boolean isEndedFrame() {
        if(stateHistoryCount() == 1) {
            if(state instanceof Strike) {
                return false;
            }
            return state.isEndedState();
        }
        if(stateHistoryCount() == 2) {
            State firstState = stateHistory.get(0);
            if(firstState instanceof Strike) {
                return false;
            }
            if(state instanceof Spare) {
                return false;
            }
            return true;
        }
        return state.isEndedState();
    }

    private int stateHistoryCount() {
        return stateHistory.size();
    }

    @Override
    public boolean isLastFrame() {
        return isEndedFrame();
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
