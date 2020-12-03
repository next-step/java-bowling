package bowling.domain.play;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.asset.Const.PIN_NUM;

abstract class NormalPlayState implements PlayState {
    private int countOfPins;

    NormalPlayState(int countOfPins) {
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
