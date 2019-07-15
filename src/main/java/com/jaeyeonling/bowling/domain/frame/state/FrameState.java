package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.FrameScore;

public abstract class FrameState {

    public abstract String toSymbol();

    public abstract FrameState bowl(final KnockdownPins knockdownPins);
    public abstract boolean isFinished();
    public abstract FrameScore calculateScore(final FrameScore frameScore);
    public abstract FrameScore getFrameScore();
}
