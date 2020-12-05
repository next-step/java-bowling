package bowling.domain.bowl;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.asset.Const.PIN_NUM;

abstract class NormalBowlState implements BowlState {
    private int countOfPins;

    NormalBowlState(int countOfPins) {
        this.countOfPins = countOfPins;
    }

    void increaseCountOfPins(int countOfPins) {
        this.countOfPins += countOfPins;
    }

    boolean isLast(int frameNo) {
        return frameNo >= MAX_FRAME_NO;
    }

    boolean isAllPinDown() {
        return countOfPins >= PIN_NUM;
    }
}
