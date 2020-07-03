package bowling.domain;

import bowling.strategy.BowlingStrategy;

public class NormalFrame extends Frame {

    @Override
    protected void setSecondPin(BowlingStrategy bowlingStrategy, int index) {
        // first pin strike
        if (pins.isFirstClean()) {
            pins.setSecondPin(bowlingStrategy.drawBowl(new Pin(NUMBER_ZERO, NUMBER_ZERO), index));
            return;
        }
        pins.setSecondPin(bowlingStrategy.drawBowl(new Pin(pins.getFirstLeftPins(), NUMBER_ZERO), index));
    }

    @Override
    protected void setThirdPin(BowlingStrategy bowlingStrategy, int index) {
        pins.setThirdPin(new Pin());
    }

    @Override
    protected State checkState() {

        if (pins.isGutter()) {
            return State.GUTTER;
        }
        if (pins.isSpare()) {
            return State.SPARE;
        }
        if (pins.isMiss()) {
            return State.MISS;
        }
        if (pins.isStrike()) {
            return State.STRIKE;
        }
        throw new IllegalStateException();

    }


}
