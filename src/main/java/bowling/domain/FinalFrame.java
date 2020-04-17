package bowling.domain;

import bowling.domain.State.LastFrameBowl;
import bowling.domain.State.State;

public class FinalFrame implements Frame {
    private static final int MAX_FRAME_NUM = 10;
    private State state;

    public FinalFrame(boolean bonusFlag) {
        this.state = new LastFrameBowl(bonusFlag);
    }

    @Override
    public Frame bowl(int felledPins) {
        state = state.bowl(felledPins);
        return this;
    }

    @Override
    public int getFrameNum() {
        return MAX_FRAME_NUM;
    }

    @Override
    public String desc() {
        return state.getDesc();
    }
}
