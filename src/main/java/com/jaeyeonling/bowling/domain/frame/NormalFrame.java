package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.frame.score.FrameScore;
import com.jaeyeonling.bowling.domain.frame.state.Ready;

public class NormalFrame extends Frame {

    private final Frame next;

    NormalFrame(final Frame next) {
        super(new Ready());
        this.next = next;
    }

    @Override
    public FrameScore calculateScore(final FrameScore base) {
        return super.calculateScore(next.calculateScore(base));
    }

    @Override
    public FrameScore getFrameScore() {
        return next.calculateScore(super.getFrameScore());
    }
}
