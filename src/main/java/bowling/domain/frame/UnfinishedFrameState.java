package bowling.domain.frame;

import bowling.domain.Pins;
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
    public int getScore(Frame frame, Pins pins) {
        return -1;
    }

    @Override
    public boolean hasScore(Frame frame, Pins pins) {
        return false;
    }

    @Override
    public void update(Frame frame, Pins pins) {
        int countOfPins = pins.sum(frame.getPinsIndex(), 2);
        if (countOfPins < 0 || countOfPins > PIN_NUM) {
            throw new BadCountOfPinsException("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
        }
        FrameState nextState = countOfPins == PIN_NUM
                ? SpareFrameState.getInstance()
                : MissFrameState.getInstance();
        frame.setState(nextState);
    }

    private static class SingletonHelper {
        private static final UnfinishedFrameState instance = new UnfinishedFrameState();
    }
}
