package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;

class Gutter extends Finished {

    Gutter() { }

    @Override
    public String toSymbol() {
        return BowlingSymbol.toSymbolFrom(KnockdownPins.MIN, KnockdownPins.MIN);
    }

    @Override
    public FrameScore calculateScore(FrameScore base) {
        base = base.calculate(getFrameScore());
        if (base.isComplete()) {
            return base;
        }

        return base.calculate(getFrameScore());
    }

    @Override
    public FrameScore getFrameScore() {
        return FrameScore.GUTTER;
    }
}
