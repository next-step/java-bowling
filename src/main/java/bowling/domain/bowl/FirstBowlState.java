package bowling.domain.bowl;

import bowling.domain.frame.Pin;

public class FirstBowlState extends BowlState {
    FirstBowlState() {
        super();
    }

    FirstBowlState(BowlState state) {
        super(state);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    void addPin(Pin pin, Bowl bowl) {
        addNextFrame(bowl);
        updateCurrentFrame(pin);
    }

    @Override
    void updateState(Bowl bowl) {
        BowlState nextState = isStrike()
                ? new StrikeFinishedBowlState(this)
                : new SecondBowlState(this);
        bowl.setState(nextState);
    }
}
