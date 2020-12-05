package bowling.domain.frame;

import bowling.domain.Pins;

class MissFrameState extends FrameState {
    MissFrameState(FrameState state) {
        super(state);
    }

    @Override
    FrameEnum getFrameEnum() {
        return FrameEnum.MISS;
    }

    @Override
    int getScore(Frame frame, Pins pins) {
        final int offset = 2;
        return pins.sum(
                frame.getPinsIndex(),
                offset
        );
    }

    @Override
    boolean hasScore(Frame frame, Pins pins) {
        return true;
    }

    @Override
    void update(Frame frame, Pins pins) {}
}
