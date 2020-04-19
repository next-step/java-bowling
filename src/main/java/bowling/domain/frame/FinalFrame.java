package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.*;

import java.util.Objects;

import static bowling.Constants.CAN_NOT_CALCULATE_SCORE;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NO = 10;
    private static final String FINAL_FRAME_ERROR = "마지막 프레임입니다.";
    private final StateHistory stateHistory;
    private State state;
    private Frame nextFrame;

    private FinalFrame(State state, StateHistory stateHistory, Frame nextFrame) {
        this.state = state;
        this.stateHistory = stateHistory;
        this.nextFrame = nextFrame;
    }

    public static Frame create() {
        return new FinalFrame(new Ready(), new StateHistory(), null);
    }

    public void play(PinCount felledPin) {
        state = state.play(felledPin);
        stateHistory.add(state);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Frame getNext() {
        throw new IllegalStateException(FINAL_FRAME_ERROR);
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NO;
    }

    @Override
    public boolean isEndedFrame() {
        int bowledCount = getBowledCount();

        if (bowledCount == 1) {
            return false;
        }
        if (bowledCount == 2) {
            return isEndedFrameBowledTwice();
        }
        return state.isEndedState();
    }

    private int getBowledCount() {
        return stateHistory.getSize();
    }

    private boolean isEndedFrameBowledTwice() {
        State firstState = stateHistory.get(0);

        if (firstState instanceof Strike) {
            return false;
        }

        if (state instanceof Spare) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isLastFrame() {
        return isEndedFrame();
    }

    @Override
    public StateHistory getStateHistory() {
        return stateHistory;
    }

    @Override
    public int getScore() {
        if(!isEndedFrame()) {
            return CAN_NOT_CALCULATE_SCORE;
        }

        Score score = ((Finished) state).createScore();

        if(score.isEnded()) {
            return score.getScore();
        }

        if(Objects.isNull(nextFrame)) {
            return CAN_NOT_CALCULATE_SCORE;
        }

        return nextFrame.calculateAdditionalScore(score);
    }

    @Override
    public int calculateAdditionalScore(Score score) {
        for(State state : stateHistory.getValue()) {
            score.addAdditionalScore(state.getFelledPin());

            if(score.isEnded()) {
                return score.getScore();
            }
        }

        if(Objects.isNull(nextFrame)) {
            return CAN_NOT_CALCULATE_SCORE;
        }

        return nextFrame.calculateAdditionalScore(score);
    }
}
