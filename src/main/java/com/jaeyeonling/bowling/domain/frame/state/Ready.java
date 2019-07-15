package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;

public class Ready extends Running {

    private static final String SYMBOL = "";

    @Override
    public String toSymbol() {
        return SYMBOL;
    }

    @Override
    public FrameState bowl(final KnockdownPins knockdownPins) {
        if (knockdownPins.isMax()) {
            return new Strike();
        }

        return new Continue(knockdownPins);
    }

    @Override
    public FrameScore calculateScore(final FrameScore base) {
        return base;
    }

    @Override
    public FrameScore getFrameScore() {
        return FrameScore.UN_SCORE;
    }
}
