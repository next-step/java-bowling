package com.jaeyeonling.bowling;

import com.jaeyeonling.bowling.frame.Frame;
import com.jaeyeonling.bowling.frame.KnockdownPins;
import com.jaeyeonling.bowling.user.User;
import com.jaeyeonling.bowling.view.StringVisualizable;

public class BowlingGame implements StringVisualizable {

    private final Frame firstFrame = Frame.first();

    private final User user;
    private Frame currentFrame = firstFrame;

    public BowlingGame(final User user) {
        this.user = user;
    }

    public void bowl(final KnockdownPins knockdownPins) {
        if (isFinish()) {
            throw new IllegalStateException();
        }

        currentFrame = currentFrame.bowl(knockdownPins);
    }

    private boolean isFinish() {
        return currentFrame.isFinish();
    }

    @Override
    public String visualize() {
        return user.visualize() + firstFrame.visualize();
    }
}
