package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.List;

import static bowling.Constants.*;

public class NormalFrame implements Frame {

    private static final int MAX_NORMAL_FRAME_COUNT = 9;
    public static final String OVER_NORMAL_FRAME_NO_ERROR = "일반 Frame은 최대 9개까지만 생성할 수 있습니다.";
    private int frameNumber;

    private State state;
    private final List<State> stateHistory = new ArrayList<>();

    public NormalFrame(int frameNumber, State state) {
        assertFrameNo(frameNumber);
        this.frameNumber = frameNumber;
        this.state = state;
    }

    public static Frame create(int frameNumber) {
        return new NormalFrame(frameNumber, new Ready());
    }

    public void play(int felledPin) {
        assertFelledPin(felledPin);
        state = state.play(felledPin);
        stateHistory.add(state);
    }

    @Override
    public Frame getNext() {
        if(frameNumber == MAX_NORMAL_FRAME_COUNT) {
            return FinalFrame.create();
        }
        return NormalFrame.create(frameNumber + 1);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
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
    public List<State> getStateHistory() {
        return stateHistory;
    }

    private void assertFelledPin(int felledPin) {
        if(felledPin > MAX_FELLED_PIN_COUNT || felledPin < MIN_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }

    private void assertFrameNo(int frameNumber) {
        if(frameNumber > MAX_NORMAL_FRAME_COUNT) {
            throw new IllegalArgumentException(OVER_NORMAL_FRAME_NO_ERROR);
        }
    }
}
