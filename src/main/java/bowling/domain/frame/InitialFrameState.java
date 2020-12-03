package bowling.domain.frame;

import bowling.domain.Rolls;
import bowling.exception.BadCountOfPinsException;

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
    public int getScore(Frame context, Rolls rolls) {
        return -1;
    }

    @Override
    public boolean hasScore(Frame context, Rolls rolls) {
        return false;
    }

    @Override
    public void update(Frame context, Rolls rolls) {
        final int offset = 1;
        int countOfPins = rolls.sum(context.getRollIndex(), offset);
        if (countOfPins < 0 || countOfPins > PIN_NUM) {
            throw new BadCountOfPinsException("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
        }
        FrameState nextState = countOfPins == PIN_NUM
                ? StrikeFrameState.getInstance()
                : UnfinishedFrameState.getInstance();
        context.setState(nextState);
    }

    private static class SingletonHelper {
        private static final InitialFrameState instance = new InitialFrameState();
    }
}
