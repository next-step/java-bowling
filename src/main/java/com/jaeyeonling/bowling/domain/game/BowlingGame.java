package com.jaeyeonling.bowling.domain.game;

import com.jaeyeonling.bowling.domain.frame.Frame;
import com.jaeyeonling.bowling.domain.frame.FrameIndex;
import com.jaeyeonling.bowling.domain.frame.KnockdownPins;
import com.jaeyeonling.bowling.domain.user.User;

public class BowlingGame {

    private final User user;
    private final BowlingGameEngine bowlingGameEngine;

    private BowlingGame(final User user) {
        this.user = user;
        this.bowlingGameEngine = new BowlingGameEngine();
    }

    public static BowlingGame with(final User user) {
        return new BowlingGame(user);
    }

    public void bowl(final KnockdownPins knockdownPins) {
        bowlingGameEngine.bowl(knockdownPins);
    }

    public boolean canPlay() {
        return bowlingGameEngine.canPlay();
    }

    public FrameIndex getCurrentFrameIndex() {
        return bowlingGameEngine.getCurrentFrameIndex();
    }

    public User getUser() {
        return user;
    }

    public Frame getFirstFrame() {
        return bowlingGameEngine.getFirstFrame();
    }
}
