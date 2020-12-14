package bowling.domain.bowl;

import bowling.domain.frame.Pin;

class FinishedBowlState extends BowlState {
    FinishedBowlState(BowlState state) {
        super(state);
    }

    @Override
    int getFrameNumberAdder() {
        return 0;
    }

    @Override
    boolean isPlayable() {
        return false;
    }

    @Override
    void addPin(Pin pin, Bowl bowl) {
        updateCurrentFrame(pin);
    }

    @Override
    void updateState(Bowl bowl) {
        BowlState nextState = bowl.isMaxFrame()
                ? new FirstMaxBowlState(this)
                : new FirstBowlState(this);
        bowl.setState(nextState);
    }
}
