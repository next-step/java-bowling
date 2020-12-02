package bowling.domain.play;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.asset.Const.PIN_NUM;

abstract class PlayState {
    abstract void playFirst(PlayContext context);

    abstract void playSecond(PlayContext context, int frameNo);

    abstract void playBonus(PlayContext context);

    boolean isLast(int frameNo) {
        return frameNo == MAX_FRAME_NO;
    }

    boolean isStrike(PlayContext context) {
        return context.getCountOfPins(1) == PIN_NUM;
    }

    boolean isSpare(PlayContext context) {
        return context.getCountOfPins(2) == PIN_NUM;
    }
}
