package com.jaeyeonling.bowling.frame.state;

import com.jaeyeonling.bowling.frame.KnockdownPins;

public class Gutter extends ValidFrameState {

    private static final KnockdownPins GUTTER_PINS = KnockdownPins.GUTTER;

    private final boolean isFinished;

    private Gutter(final boolean isFinished) {
        this.isFinished = isFinished;
    }

    Gutter() {
        this(false);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    FrameState validBowl(final KnockdownPins knockdownPins) {
        if (knockdownPins.isMax()) {
            return new Spare(KnockdownPins.GUTTER);
        }
        if (knockdownPins.isGutter()) {
            return new Gutter(true);
        }

        return new Miss(KnockdownPins.GUTTER, knockdownPins);
    }

    @Override
    public String visualize() {
        return isFinished ? GUTTER_PINS.toSymbol(GUTTER_PINS) : GUTTER_PINS.toSymbol();
    }
}
