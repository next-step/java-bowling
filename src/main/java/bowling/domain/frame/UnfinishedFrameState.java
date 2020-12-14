package bowling.domain.frame;

import bowling.exception.BadCountOfDownPinsException;

import static bowling.asset.Const.MAX_PIN_NUMBER;

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
        int countOfDownPins = getCountOfDownPins();
        if (countOfDownPins < 0 || countOfDownPins > MAX_PIN_NUMBER) {
            throw new BadCountOfDownPinsException("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
        }
        FrameState nextState = countOfDownPins == MAX_PIN_NUMBER
                ? new SpareFrameState(this)
                : new MissFrameState(this);
        frame.setState(nextState);
    }
}
