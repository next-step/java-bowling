package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.frame.score.FrameScore;
import com.jaeyeonling.bowling.domain.frame.state.FrameState;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;

public abstract class Frame {

    private FrameState frameState;

    Frame(final FrameState frameState) {
        this.frameState = frameState;
    }

    public FrameScore calculateScore(final FrameScore base) {
        return frameState.calculateScore(base);
    }

    public FrameState getFrameState() {
        return frameState;
    }

    public FrameScore getFrameScore() {
        return frameState.getFrameScore();
    }

    boolean isFinished() {
        return frameState.isFinished();
    }

    void bowl(final KnockdownPins knockdownPins) {
        frameState = frameState.bowl(knockdownPins);
    }
}
