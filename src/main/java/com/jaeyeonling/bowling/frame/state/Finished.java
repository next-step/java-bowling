package com.jaeyeonling.bowling.frame.state;

import com.jaeyeonling.bowling.frame.KnockdownPins;

public abstract class Finished extends ValidFrameState {

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    FrameState validBowl(final KnockdownPins ignore) {
        throw new AlreadyFinishedFrameStateException();
    }
}
