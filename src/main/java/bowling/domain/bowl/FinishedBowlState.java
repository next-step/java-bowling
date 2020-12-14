package bowling.domain.bowl;

import bowling.domain.frame.Pin;

abstract class FinishedBowlState extends BowlState {
    FinishedBowlState(BowlState state) {
        super(state);
    }

    @Override
    boolean isPlayable() {
        return false;
    }

    @Override
    void addPin(Pin pin, Bowl bowl) {
        updateCurrentFrame(pin);
    }
}
