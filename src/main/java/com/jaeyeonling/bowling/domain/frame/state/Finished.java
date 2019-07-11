package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.frame.KnockdownPins;

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
