package com.jaeyeonling.bowling.frame;

import com.jaeyeonling.bowling.frame.state.*;

public class FinalFrame extends StateFrame {

    FinalFrame() {
        super(FrameIndex.last(), new FinalState());
    }

    @Override
    public Frame bowl(final KnockdownPins knockdownPins) {
        if (isFinish()) {
            throw new IllegalStateException();
        }

        frameState = frameState.bowl(knockdownPins);

        return this;
    }

    @Override
    public boolean isFinish() {
        return frameState.isFinished();
    }
}
