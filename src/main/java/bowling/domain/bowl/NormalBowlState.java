package bowling.domain.bowl;

import bowling.domain.Pin;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.asset.Const.PIN_NUM;

abstract class NormalBowlState implements BowlState {
    private int countOfPins;

    NormalBowlState() {
        countOfPins = 0;
    }

    NormalBowlState(NormalBowlState state) {
        countOfPins = state.countOfPins;
    }

    void increaseCountOfPins(Pin pin) {
        countOfPins = pin.sum(countOfPins);
    }

    boolean isLast(int frameNumber) {
        return frameNumber >= MAX_FRAME_NO;
    }

    boolean isAllPinDown() {
        return countOfPins >= PIN_NUM;
    }
}
