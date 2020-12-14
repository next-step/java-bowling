package bowling.domain.bowl;

import bowling.domain.frame.Pin;

class GameOverBowlState extends BowlState {
    GameOverBowlState(BowlState state) {
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
    void addPin(Pin pin, Bowl bowl) {}

    @Override
    void updateState(Bowl bowl) {}
}
