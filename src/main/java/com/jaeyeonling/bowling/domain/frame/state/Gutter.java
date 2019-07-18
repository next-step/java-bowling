package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;

public class Gutter extends Finished {

    private static final int REMAINING_COUNT = 0;

    private static final KnockdownPins KNOCKDOWN_PINS = KnockdownPins.MIN;
    
    public static final FrameScore SCORE = FrameScore.of(KNOCKDOWN_PINS.getKnockdownPins(), REMAINING_COUNT);

    Gutter() { }

    @Override
    public String toSymbol() {
        return BowlingSymbol.toSymbolFrom(KNOCKDOWN_PINS, KNOCKDOWN_PINS);
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
        return SCORE;
    }
}
