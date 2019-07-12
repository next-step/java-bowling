package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.frame.state.FrameState;
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
    public String getFrameState() {
        return BowlingUtils.format(frameState.toSymbol()) + BowlingSymbol.DELIMITER;
    }

    @Override
    public FrameIndex getFrameIndex() {
        return frameIndex;
    }
}
