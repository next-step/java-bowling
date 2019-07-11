package com.jaeyeonling.bowling.domain;

import com.jaeyeonling.bowling.domain.frame.FinishedBowlingGameException;
import com.jaeyeonling.bowling.domain.frame.Frame;
import com.jaeyeonling.bowling.domain.frame.FrameIndex;
import com.jaeyeonling.bowling.domain.frame.KnockdownPins;
import com.jaeyeonling.bowling.domain.user.User;
import com.jaeyeonling.bowling.view.StringVisualizable;

public class BowlingGame implements StringVisualizable {

    private final Frame firstFrame = Frame.first();

    private final User user;
    private Frame currentFrame = firstFrame;

    private BowlingGame(final User user) {
        this.user = user;
    }

    public static BowlingGame with(final User user) {
        return new BowlingGame(user);
    }

    public void bowl(final KnockdownPins knockdownPins) {
        if (isFinish()) {
            throw new FinishedBowlingGameException();
        }

        currentFrame = currentFrame.bowl(knockdownPins);
    }

    public boolean canPlay() {
        return !isFinish();
    }

    public FrameIndex getCurrentFrameIndex() {
        return currentFrame.getFrameIndex();
    }

    @Override
    public String visualize() {
        return user.visualize() + firstFrame.visualize();
    }

    private boolean isFinish() {
        return currentFrame.isFinish();
    }
}
