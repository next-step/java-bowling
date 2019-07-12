package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.frame.KnockdownPins;

public interface FrameState {

    String toSymbol();
    boolean isFinished();
    FrameState bowl(final KnockdownPins knockdownPins);
}
