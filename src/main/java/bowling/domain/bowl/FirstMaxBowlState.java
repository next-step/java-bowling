package bowling.domain.bowl;

import bowling.domain.frame.Pin;

import static bowling.domain.frame.FrameEnum.STRIKE;

class FirstMaxBowlState extends BowlState {
    FirstMaxBowlState(BowlState state) {
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
        addNextFrame(bowl);
        updateCurrentFrame(pin);
    }

    @Override
    void updateState(Bowl bowl) {
        BowlState nextState = getFrameEnum() == STRIKE
                ? new StrikeBonusBowlState(this)
                : new SecondMaxBowlState(this);
        bowl.setState(nextState);
    }
}
