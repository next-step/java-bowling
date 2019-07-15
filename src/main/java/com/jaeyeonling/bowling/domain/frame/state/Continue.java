package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;

class Continue extends Running {

    private static final int REMAINING_COUNT = 1;

    private final KnockdownPins first;

    Continue(final KnockdownPins first) {
        this.first = first;
    }

    @Override
    public String toSymbol() {
        return BowlingSymbol.toSymbolFrom(first);
    }

    @Override
    public FrameState bowl(final KnockdownPins second) {
        final KnockdownPins sumOfKnockdownPins = first.sum(second);
        if (sumOfKnockdownPins.isMax()) {
            return new Spare(first);
        }
        if (sumOfKnockdownPins.isMin()) {
            return new Gutter();
        }

        return new Miss(first, second);
    }

    @Override
    public FrameScore calculateScore(final FrameScore base) {
        return base.calculate(getFrameScore());
    }

    @Override
    public FrameScore getFrameScore() {
        return FrameScore.of(first.getKnockdownPins(), REMAINING_COUNT);
    }
}
