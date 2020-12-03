package bowling.domain.play;

import bowling.domain.Rolls;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.asset.Const.PIN_NUM;

abstract class PlayState {
    abstract void playFirst(PlayContext context);

    abstract void playSecond(PlayContext context, int frameNo);

    abstract void playBonus(PlayContext context);

    boolean isLast(int frameNo) {
        return frameNo >= MAX_FRAME_NO;
    }

    boolean isAllPinDown(PlayContext context, int countOfRolls) {
        return getCountOfPins(context, countOfRolls) >= PIN_NUM;
    }

    private int getCountOfPins(PlayContext context, int countOfRolls) {
        Rolls rolls = context.getRolls();
        int rollIndex = rolls.size() - countOfRolls;
        return rolls.sum(rollIndex, countOfRolls);
    }
}
