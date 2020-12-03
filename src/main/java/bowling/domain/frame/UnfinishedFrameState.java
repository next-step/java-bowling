package bowling.domain.frame;

import bowling.domain.Rolls;
import bowling.exception.BadCountOfPinsException;

import static bowling.asset.Const.PIN_NUM;

public class UnfinishedFrameState implements FrameState {
    private UnfinishedFrameState() {}

    static UnfinishedFrameState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public FrameEnum getFrameEnum() {
        return FrameEnum.UNFINISHED;
    }

    @Override
    public int getScore(Frame context, Rolls rolls) {
        return -1;
    }

    @Override
    public boolean hasScore(Frame context, Rolls rolls) {
        return false;
    }

    @Override
    public void update(Frame context, Rolls rolls) {
        int countOfPins = rolls.sum(context.getRollIndex(), 2);
        if (countOfPins < 0 || countOfPins > PIN_NUM) {
            throw new BadCountOfPinsException("한 프레임에서 쓰러트린 핀의 갯수는 0이상 10이하 여야 합니다.");
        }
        FrameState nextState = countOfPins == PIN_NUM
                ? SpareFrameState.getInstance()
                : MissFrameState.getInstance();
        context.setState(nextState);
    }

    private static class SingletonHelper {
        private static final UnfinishedFrameState instance = new UnfinishedFrameState();
    }
}
