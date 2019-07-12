package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.frame.KnockdownPins;

public class Ready extends ValidFrameState {

    private static final String SYMBOL = "";

    @Override
    FrameState validBowl(final KnockdownPins knockdownPins) {
        if (knockdownPins.isMax()) {
            return new Strike();
        }
        if (knockdownPins.isGutter()) {
            return new Gutter();
        }

        return Miss.of(knockdownPins);
    }

    @Override
    public String visualize() {
        return SYMBOL;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
