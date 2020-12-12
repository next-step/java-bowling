package bowling.domain.frame;

import bowling.exception.BadCountOfPinsException;

import static bowling.asset.Const.PIN_NUM;

class UnfinishedFrameState extends FrameState {
    UnfinishedFrameState(FrameState state) {
        super(state);
    }

    @Override
    int getOffset() {
        return 2;
    }

    @Override
    FrameEnum getFrameEnum() {
        return FrameEnum.UNFINISHED;
    }

    @Override
    void updateState(Frame frame) {
        int countOfPins = getCountOfPins();
        if (countOfPins < 0 || countOfPins > PIN_NUM) {
            throw new BadCountOfPinsException("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
        }
        FrameState nextState = countOfPins == PIN_NUM
                ? new SpareFrameState(this)
                : new MissFrameState(this);
        frame.setState(nextState);
    }
}
