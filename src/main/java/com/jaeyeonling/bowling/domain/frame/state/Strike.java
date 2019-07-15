package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;

@HaveBonus
class Strike extends Finished {

    private static final KnockdownPins KNOCKDOWN_PINS = KnockdownPins.valueOf(10);

    Strike() { }

    @Override
    public String toSymbol() {
        return BowlingSymbol.toSymbolFrom(KNOCKDOWN_PINS);
    }

    @Override
    public FrameScore calculateScore(final FrameScore frameScore) {
        return frameScore.calculate(getFrameScore());
    }

    @Override
    public FrameScore getFrameScore() {
        return FrameScore.STRIKE;
    }
}
