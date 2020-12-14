package bowling.domain.bowl;

import bowling.domain.frame.Pin;

class SecondBowlState extends BowlState {
    SecondBowlState(BowlState state) {
        super(state);
    }

    @Override
    int getFrameNumberAdder() {
        return 0;
    }

    @Override
    boolean isPlayable() {
        return true;
    }

    @Override
    void addPin(Pin pin, Bowl bowl) {
        updateCurrentFrame(pin);
    }

    @Override
    void updateState(Bowl bowl) {
        bowl.setState(new FinishedBowlState(this));
    }
}
