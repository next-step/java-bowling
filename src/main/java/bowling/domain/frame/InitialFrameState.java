package bowling.domain.frame;

import static bowling.asset.Const.PIN_NUM;

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
        FrameState nextState = getCountOfPins() == PIN_NUM
                ? new StrikeFrameState(this)
                : new UnfinishedFrameState(this);
        frame.setState(nextState);
    }
}
