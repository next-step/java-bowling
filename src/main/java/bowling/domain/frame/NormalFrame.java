package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.*;

import static bowling.Constants.NOT_ENDED_FRAME_ERROR;

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

    public void play(PinCount felledPin) {
        state = state.play(felledPin);
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

    @Override
    public int getScore() {
        if(!isEndedFrame()) {
            throw new IllegalStateException(NOT_ENDED_FRAME_ERROR);
        }

        Score score = ((Finished) state).createScore();

        if(!score.canCalculateScore()) {
            return score.getScore();
        }

        return getNext().calculateAdditionalScore(score);
    }

    @Override
    public int calculateAdditionalScore(Score score) {
        for(State state : stateHistory.getValue()) {
            score.addAdditionalScore(state.getFelledPin());

            if(!score.canCalculateScore()) {
                return score.getScore();
            }
        }

        return getNext().calculateAdditionalScore(score);
    }

    @Override
    public boolean canCalculateScore() {
        return !isEndedFrame();
    }
}
