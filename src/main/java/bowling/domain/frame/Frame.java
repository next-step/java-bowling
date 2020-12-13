package bowling.domain.frame;

import bowling.domain.FallingPinCount;
import bowling.domain.state.InitState;
import bowling.domain.state.State;

public class Frame {

    private static final int INCREMENTAL = 1;
    private static final int FINAL_INDEX = 10;

    private final int index;
    private State state;

    public Frame(int index) {
        this.index = index;
        this.state = InitState.getInstance();
    }

    public boolean isFrameFinished() {
        return state.isDone();
    }

    public Frame roll(FallingPinCount fallingPinCount) {
        state = state.roll(fallingPinCount);
        if (isFrameFinished()) {
            return getNextFrame();
        }
        return this;
    }

    public int getFrameSequence() {
        return index;
    }

    public State state() {
        return state;
    }

    private Frame getNextFrame() {
        int nextIndex = index + INCREMENTAL;

        if (isNextFinal(nextIndex)) {
            return new FinalFrame(nextIndex);
        }
        return new Frame(nextIndex);
    }

    private boolean isNextFinal(int nextIndex) {
        return nextIndex == FINAL_INDEX;
    }
}
