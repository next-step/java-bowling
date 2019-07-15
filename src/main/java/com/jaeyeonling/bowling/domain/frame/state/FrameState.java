package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;

public interface FrameState {

    String toSymbol();
    FrameState bowl(final KnockdownPins knockdownPins);
    boolean isFinished();
    FrameScore calculateScore(final FrameScore frameScore);
    FrameScore getFrameScore();
}
