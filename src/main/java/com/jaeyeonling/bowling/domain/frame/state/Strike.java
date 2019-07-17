package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;

@HaveBonus
public class Strike extends Finished {

    private static final KnockdownPins KNOCKDOWN_PINS = KnockdownPins.MAX;

    public static final FrameScore SCORE = FrameScore.of(KNOCKDOWN_PINS.getKnockdownPins(), 2);

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
        return SCORE;
    }
}
