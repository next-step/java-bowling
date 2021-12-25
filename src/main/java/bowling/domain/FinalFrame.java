package bowling.domain;

import bowling.domain.state.State;

public class FinalFrame extends AbstractFrame {
    public static final String FINAL_FRAME_MESSAGE = "10프레임 이후 프레임은 없습니다.";

    private static final int TEN = 10;

    public FinalFrame() {
        super(new FrameRoundNumber(TEN));
    }

    @Override
    public Frame addNextFrame() {
        throw new IllegalArgumentException(FINAL_FRAME_MESSAGE);
    }

    @Override
    public boolean isNinthFrame() {
        return false;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return state.isFinished() && !state.hasBonus();
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public State getState() {
        return state;
    }
}
