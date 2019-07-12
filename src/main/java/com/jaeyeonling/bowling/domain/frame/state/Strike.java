package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.frame.KnockdownPins;

public class Strike extends Finished {

    private static final KnockdownPins STRIKE_PINS = KnockdownPins.MAX;

    @Override
    public String visualize() {
        return STRIKE_PINS.toSymbol();
    }
}
