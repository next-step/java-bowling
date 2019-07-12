package com.jaeyeonling.bowling.domain.game;

import com.jaeyeonling.bowling.domain.frame.FrameIndex;
import com.jaeyeonling.bowling.domain.frame.KnockdownPins;
import com.jaeyeonling.bowling.domain.user.User;
import com.jaeyeonling.bowling.view.StringVisualizable;

public class BowlingGame implements StringVisualizable {

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

    @Override
    public String visualize() {
        return user.visualize() + bowlingGameEngine.visualize();
    }
}
