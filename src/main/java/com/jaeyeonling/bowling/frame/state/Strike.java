package com.jaeyeonling.bowling.frame.state;

import com.jaeyeonling.bowling.frame.KnockdownPins;

public class Strike extends Finished {

    private static final KnockdownPins STRIKE_PINS = KnockdownPins.MAX;

    @Override
    public String visualize() {
        return STRIKE_PINS.toSymbol();
    }
}
