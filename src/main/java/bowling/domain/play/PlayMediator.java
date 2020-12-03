package bowling.domain.play;

import bowling.domain.Rolls;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.asset.Const.PIN_NUM;

/**
 * NOTE: Mediator 에는 Context 의 State 를 바꾸는 로직만 있어야 한다.
 * 다른 상태나 객체값을 변화시키지 않도록 조심하자.
 */
class PlayMediator {
    private PlayMediator() {}

    static void notifyFirst(PlayContext context, int frameNo) {
        Rolls rolls = context.getRolls();
        PlayState nextState = isLast(frameNo) && isStrike(rolls)
                ? LastStrikePlayState.getInstance()
                : isStrike(rolls)
                ? StrikePlayState.getInstance()
                : NormalPlayState.getInstance();
        context.setState(nextState);
    }

    static void notifySecond(PlayContext context, int frameNo) {
        Rolls rolls = context.getRolls();
        PlayState nextState = isLast(frameNo) && isSpare(rolls)
                ? LastSparePlayState.getInstance()
                : isLast(frameNo)
                ? GameOverPlayState.getInstance()
                : NormalPlayState.getInstance();
        context.setState(nextState);
    }

    static void notifyBonus(PlayContext context, int frameNo) {
        if (isLast(frameNo)) {
            context.setState(GameOverPlayState.getInstance());
        }
    }

    private static boolean isLast(int frameNo) {
        return frameNo >= MAX_FRAME_NO;
    }

    private static boolean isStrike(Rolls rolls) {
        return getCountOfPins(rolls, 1) == PIN_NUM;
    }

    private static boolean isSpare(Rolls rolls) {
        return getCountOfPins(rolls, 2) == PIN_NUM;
    }

    private static int getCountOfPins(Rolls rolls, int countOfRolls) {
        int rollIndex = rolls.size() - countOfRolls;
        return rolls.sum(rollIndex, countOfRolls);
    }
}
