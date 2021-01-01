package step2.domain.frame;

import step2.domain.Score;
import step2.domain.state.State;
import step2.domain.state.StateFactory;

public class NormalFrame implements Frame {

    private final int index;
    private State state;

    public NormalFrame(int index) {
        this.index = index;
        this.state = StateFactory.ready();
    }

    public static Frame init() {
        return new NormalFrame(DEFAULT);
    }

    @Override
    public Frame bowl(int fallingPins) {
        state = state.bowl(fallingPins);
        if (state.isFinish()) {
            return createFrame(index);
        }
        return this;
    }

    private Frame createFrame(int index) {
        if (index == FINAL_FRAME_NO - 1) {
            return new FinalFrame();
        }

        int nextIndex = index++;
        return new NormalFrame(nextIndex);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return null;
    }
}
