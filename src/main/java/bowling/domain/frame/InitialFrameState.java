package bowling.domain.frame;

import bowling.domain.FrameEnum;
import bowling.domain.Rolls;
import bowling.exception.BadCountOfPins;

import static bowling.asset.Const.PIN_NUM;

public class InitialFrameState implements FrameState {
    private InitialFrameState() {}

    static InitialFrameState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public FrameEnum getFrameEnum() {
        return FrameEnum.UNFINISHED;
    }

    @Override
    public int getScore(FrameContext context, Rolls rolls) {
        return -1;
    }

    @Override
    public void update(FrameContext context, Rolls rolls) {
        int countOfPins = rolls.sum(context.getRollIndex(), 1);
        if (countOfPins < 0 || countOfPins > PIN_NUM) {
            throw new BadCountOfPins("쓰러트린 핀의 갯수는 0이상 10이하 여야 합니다.");
        }
        if (countOfPins == PIN_NUM) {
            context.setState(StrikeFrameState.getInstance());
            return;
        }
        context.setState(UnfinishedFrameState.getInstance());
    }

    private static class SingletonHelper {
        private static final InitialFrameState instance = new InitialFrameState();
    }
}
