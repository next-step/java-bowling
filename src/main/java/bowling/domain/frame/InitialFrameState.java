package bowling.domain.frame;

import static bowling.asset.Const.PIN_NUMBER;

class InitialFrameState extends FrameState {
    InitialFrameState(Pins pins, int pinsIndex) {
        super(pins, pinsIndex);
    }

    @Override
    int getOffset() {
        return 1;
    }

    @Override
    FrameEnum getFrameEnum() {
        return FrameEnum.UNFINISHED;
    }

    @Override
    void updateState(Frame frame) {
        FrameState nextState = getCountOfPins() == PIN_NUMBER
                ? new StrikeFrameState(this)
                : new UnfinishedFrameState(this);
        frame.setState(nextState);
    }
}
