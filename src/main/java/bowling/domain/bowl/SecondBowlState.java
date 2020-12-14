package bowling.domain.bowl;

import bowling.domain.frame.Pin;

import static bowling.domain.frame.FrameEnum.SPARE;

class SecondBowlState extends BowlState {
    SecondBowlState(BowlState state) {
        super(state);
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
        BowlState nextState = getFrameEnum() == SPARE
                ? new SpareFinishedBowlState(this)
                : new MissFinishedBowlState(this);
        bowl.setState(nextState);
    }
}
