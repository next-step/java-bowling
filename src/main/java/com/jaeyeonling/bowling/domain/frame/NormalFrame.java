package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.frame.state.Ready;

public class NormalFrame extends StateFrame {

    private final Frame nextFrame = getNextFrame();

    private NormalFrame(final FrameIndex frameIndex) {
        super(frameIndex, new Ready());
    }

    public static Frame first() {
        return new NormalFrame(FrameIndex.first());
    }

    @Override
    public Frame bowl(final KnockdownPins knockdownPins) {
        frameState = frameState.bowl(knockdownPins);
        if (frameState.isFinished()) {
            return nextFrame;
        }

        return this;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    private Frame getNextFrame() {
        final FrameIndex nextIndex = nextIndex();
        if (nextIndex.isMax()) {
            return new FinalFrame();
        }

        return new NormalFrame(nextIndex);
    }

    @Override
    public String getFrameState() {
        return super.getFrameState() + nextFrame.getFrameState();
    }
}
