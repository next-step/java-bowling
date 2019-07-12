package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.frame.KnockdownPins;

public abstract class ValidFrameState implements FrameState {

    abstract FrameState validBowl(final KnockdownPins knockdownPins);

    @Override
    public FrameState bowl(final KnockdownPins knockdownPins) {
        if (isFinished()) {
            throw new AlreadyFinishedFrameStateException();
        }

        return validBowl(knockdownPins);
    }
}
