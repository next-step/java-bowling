package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;

abstract class Finished implements FrameState {

    Finished() { }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public FrameState bowl(final KnockdownPins ignore) {
        throw new FinishedFrameStateException();
    }
}
