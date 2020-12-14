package bowling.domain.bowl;

import bowling.domain.frame.Pin;

import static bowling.domain.frame.FrameEnum.STRIKE;

class FirstBowlState extends BowlState {
    FirstBowlState() {
        super();
    }

    FirstBowlState(BowlState state) {
        super(state);
    }

    @Override
    int getFrameNumberAdder() {
        return 1;
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
                ? new FinishedBowlState(this)
                : new SecondBowlState(this);
        bowl.setState(nextState);
    }
}
