package com.jaeyeonling.bowling.domain.game;

import com.jaeyeonling.bowling.domain.frame.*;

class BowlingGameEngine {

    private final Frame firstFrame = NormalFrame.first();
    private Frame currentFrame = firstFrame;

    BowlingGameEngine() { }

    void bowl(final KnockdownPins knockdownPins) {
        if (isFinish()) {
            throw new FinishedBowlingGameException();
        }

        currentFrame = currentFrame.bowl(knockdownPins);
    }

    boolean canPlay() {
        return !isFinish();
    }

    FrameIndex getCurrentFrameIndex() {
        return currentFrame.getFrameIndex();
    }

    Frame getFirstFrame() {
        return firstFrame;
    }

    private boolean isFinish() {
        return currentFrame.isFinish();
    }
}
