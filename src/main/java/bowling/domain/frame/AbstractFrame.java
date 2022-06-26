package bowling.domain.frame;

import bowling.domain.state.AbstractState;
import bowling.domain.state.FullFrameState;

public abstract class AbstractFrame implements Frame {
    static final int NOT_COMPLETED_CALCULATION = 0;
    static final int CALCULATE_TWICE = 2;
    static final int CALCULATE_ONCE = 1;

    FullFrameState fullFrameState;
    AbstractFrame next;

    AbstractFrame() {
        this.fullFrameState = new FullFrameState();
        this.next = null;
    }

    public abstract void bowl(int fallenPinsCount);

    public abstract boolean capableOfAdditionalBowling();

    public abstract int getScore();

    public AbstractState getFirstHalfFrameState() {
        return fullFrameState.getFirstHalfFrameState();
    }

    public int fallenPinsCountOfFirstFrame() {
        return fullFrameState.fallenPinsCountOfFirstFrame();
    }

    public String symbolOfFirstFrame() {
        return fullFrameState.symbolOfFirstFrame();
    }

    public AbstractState getSecondHalfFrameState() {
        return fullFrameState.getSecondHalfFrameState();
    }

    int fallenPinsCountOfSecondFrame() {
        return fullFrameState.fallenPinsCountOfSecondFrame();
    }

    public String symbolOfSecondFrame() {
        return fullFrameState.symbolOfSecondFrame();
    }

    AbstractFrame afterNext() {
        return next.next;
    }
}
