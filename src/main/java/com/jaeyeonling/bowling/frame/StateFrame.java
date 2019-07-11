package com.jaeyeonling.bowling.frame;

import com.jaeyeonling.bowling.frame.state.FrameState;
import com.jaeyeonling.bowling.utils.BowlingUtils;

public abstract class StateFrame implements Frame {

    private final FrameIndex frameIndex;

    FrameState frameState;

    StateFrame(final FrameIndex frameIndex,
               final FrameState frameState) {
        this.frameIndex = frameIndex;
        this.frameState = frameState;
    }

    FrameIndex nextIndex() {
        return frameIndex.next();
    }

    @Override
    public String visualize() {
        return BowlingUtils.format(frameState.visualize()) + BowlingSymbol.DELIMITER;
    }
}
