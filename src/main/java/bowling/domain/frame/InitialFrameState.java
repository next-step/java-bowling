package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.exception.BadCountOfPinsException;

import static bowling.asset.Const.PIN_NUM;

class InitialFrameState extends FrameState {
    InitialFrameState(int pinsIndex) {
        super(pinsIndex);
    }

    @Override
    FrameEnum getFrameEnum() {
        return FrameEnum.UNFINISHED;
    }

    @Override
    int getScore(Frame frame, Pins pins) {
        return -1;
    }

    @Override
    boolean hasScore(Frame frame, Pins pins) {
        return false;
    }

    @Override
    void update(Frame frame, Pins pins) {
        final int offset = 1;
        int countOfPins = pins.sum(frame.getPinsIndex(), offset);
        if (countOfPins < 0 || countOfPins > PIN_NUM) {
            throw new BadCountOfPinsException("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
        }
        FrameState nextState = countOfPins == PIN_NUM
                ? new StrikeFrameState(this)
                : new UnfinishedFrameState(this);
        frame.setState(nextState);
    }
}
