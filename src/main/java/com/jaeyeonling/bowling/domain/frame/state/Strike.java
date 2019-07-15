package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;

@HaveBonus
class Strike extends Finished {

    Strike() { }

    @Override
    public String toSymbol() {
        return BowlingSymbol.toSymbolFrom(KnockdownPins.MAX);
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
