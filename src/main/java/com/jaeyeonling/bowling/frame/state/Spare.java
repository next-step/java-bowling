package com.jaeyeonling.bowling.frame.state;

import com.jaeyeonling.bowling.frame.KnockdownPins;

public class Spare extends Finished {

    private final KnockdownPins knockdownPins;

    Spare(final KnockdownPins knockdownPins) {
        this.knockdownPins = knockdownPins;
    }

    @Override
    public String visualize() {
        return knockdownPins.toSymbol(knockdownPins.remaining());
    }
}
