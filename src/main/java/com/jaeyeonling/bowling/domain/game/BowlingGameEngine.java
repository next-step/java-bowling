package com.jaeyeonling.bowling.domain.game;

import com.jaeyeonling.bowling.domain.frame.FinishedBowlingGameException;
import com.jaeyeonling.bowling.domain.frame.Frame;
import com.jaeyeonling.bowling.domain.frame.FrameIndex;
import com.jaeyeonling.bowling.domain.frame.KnockdownPins;
import com.jaeyeonling.bowling.view.StringVisualizable;

class BowlingGameEngine implements StringVisualizable {

    private final Frame firstFrame = Frame.first();
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

    @Override
    public String visualize() {
        return firstFrame.visualize();
    }

    private boolean isFinish() {
        return currentFrame.isFinish();
    }
}
