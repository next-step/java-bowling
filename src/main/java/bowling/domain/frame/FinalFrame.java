package bowling.domain.frame;

import bowling.domain.state.Pin;
import bowling.domain.state.State;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    @Override
    public Frame bowl(Pin fallenPins) {
        return null;
    }

    @Override
    public Score calculateByBeforeScore(Score beforeScore) {
        return null;
    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public void updateScore(Score score) {

    }

    @Override
    public Result getFrameResult() {
        return null;
    }
}
