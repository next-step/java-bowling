package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.FrameScore;

@HaveBonus
class Spare extends Finished {

    private final KnockdownPins first;

    Spare(final KnockdownPins first) {
        this.first = first;
    }

    @Override
    public String toSymbol() {
        return BowlingSymbol.toSymbolFrom(first, first.remaining());
    }

    @Override
    public FrameScore calculateScore(FrameScore base) {
        base = base.calculate(getFirstScore());
        if (base.isComplete()) {
            return base;
        }

        return base.calculate(getSecondScore());
    }

    @Override
    public FrameScore getFrameScore() {
        return FrameScore.SPARE;
    }

    private FrameScore getFirstScore() {
        return FrameScore.of(first.getKnockdownPins());
    }

    private FrameScore getSecondScore() {
        return FrameScore.of(first.remaining().getKnockdownPins());
    }
}
