package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.frame.score.FrameScore;
import com.jaeyeonling.bowling.domain.frame.state.FrameState;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;

public abstract class Frame {

    private FrameState frameState;
    private FrameScore frameScore;

    Frame(final FrameState frameState) {
        this.frameState = frameState;
        frameScore = frameState.getFrameScore();
    }

    public FrameScore calculateScore(final FrameScore base) {
        if (base.isComplete()) {
            return base;
        }

        return frameState.calculateScore(base);
    }

    public FrameScore getFrameScore() {
        return frameScore;
    }

    boolean isFinished() {
        return frameState.isFinished();
    }

    void bowl(final KnockdownPins knockdownPins) {
        frameState = frameState.bowl(knockdownPins);
        frameScore = frameState.getFrameScore();
    }
}
