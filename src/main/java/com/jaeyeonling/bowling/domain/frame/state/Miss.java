package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.FrameScore;

class Miss extends Finished {

    private final KnockdownPins first;
    private final KnockdownPins second;

    Miss(final KnockdownPins first,
         final KnockdownPins second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toSymbol() {
        return BowlingSymbol.toSymbolFrom(first, second);
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
        return getFirstScore().sum(getSecondScore());
    }

    private FrameScore getFirstScore() {
        return FrameScore.of(first.getKnockdownPins());
    }

    private FrameScore getSecondScore() {
        return FrameScore.of(second.getKnockdownPins());
    }
}
