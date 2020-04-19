package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.*;

import java.util.Objects;

import static bowling.Constants.CAN_NOT_CALCULATE_SCORE;

public class NormalFrame implements Frame {
    private final StateHistory stateHistory;
    private FrameNumber frameNumber;
    private State state;
    private Frame nextFrame;

    private NormalFrame(FrameNumber frameNumber, State state, StateHistory stateHistory, Frame nextFrame) {
        this.frameNumber = frameNumber;
        this.state = state;
        this.stateHistory = stateHistory;
        this.nextFrame = nextFrame;
    }

    public static Frame create(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber, new Ready(), new StateHistory(), null);
    }

    public void play(PinCount felledPin) {
        state = state.play(felledPin);
        stateHistory.add(state);
    }

    @Override
    public Frame getNext() {
        if (frameNumber.isMaxNormalFrameCount()) {
            setNext(FinalFrame.create());
            return this.nextFrame;
        }
        setNext(NormalFrame.create(frameNumber.getNext()));
        return this.nextFrame;
    }

    public void setNext(Frame nextFrame) {
        this.nextFrame = nextFrame;
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

    @Override
    public int getScore() {
        if (!isEndedFrame()) {
            return CAN_NOT_CALCULATE_SCORE;
        }

        Score score = ((Finished) state).createScore();

        if (score.isEnded()) {
            return score.getScore();
        }

        if (Objects.isNull(nextFrame)) {
            return CAN_NOT_CALCULATE_SCORE;
        }

        return nextFrame.calculateAdditionalScore(score);
    }

    @Override
    public int calculateAdditionalScore(Score score) {
        for (State state : stateHistory.getValue()) {
            score.addAdditionalScore(state.getFelledPin());

            if (score.isEnded()) {
                return score.getScore();
            }
        }

        if (Objects.isNull(nextFrame)) {
            return CAN_NOT_CALCULATE_SCORE;
        }

        return nextFrame.calculateAdditionalScore(score);
    }

    @Override
    public boolean canCalculateScore() {
        return getScore() != CAN_NOT_CALCULATE_SCORE;
    }
}
