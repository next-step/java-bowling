package com.jaeyeonling.bowling.domain;

import com.jaeyeonling.bowling.domain.frame.Frames;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.user.User;

public class BowlingGame {

    private final User user;
    private final Frames frames;

    private BowlingGame(final User user,
                        final Frames frames) {
        this.user = user;
        this.frames = frames;
    }

    public static BowlingGame with(final User user) {
        return new BowlingGame(user, new Frames());
    }

    public boolean canPlay() {
        return !frames.isFinished();
    }

    public void bowl(final KnockdownPins knockdownPins) {
        frames.bowl(knockdownPins);
    }

    public User getUser() {
        return user;
    }

    public Frames getFrames() {
        return frames;
    }

    public int getCurrentFrameIndex() {
        return frames.getCurrentFrameIndex();
    }
}
