package bowling.domain.frame;

import static bowling.asset.Const.MAX_PIN_NUMBER;

class InitialFrameState extends FrameState {
    InitialFrameState(Pins pins, int pinsIndex) {
        super(pins, pinsIndex);
    }

    @Override
    int getOffset() {
        return 1;
    }

    @Override
    FrameStatus getFrameStatus() {
        return FrameStatus.UNFINISHED;
    }

    @Override
    void updateState(Frame frame) {
        FrameState nextState = getCountOfDownPins() == MAX_PIN_NUMBER
                ? new StrikeFrameState(this)
                : new UnfinishedFrameState(this);
        frame.setState(nextState);
    }
}
