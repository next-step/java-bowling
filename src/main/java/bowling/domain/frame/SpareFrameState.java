package bowling.domain.frame;

import bowling.domain.Pins;

class SpareFrameState extends FrameState {
    private final int offset = 3;

    SpareFrameState(FrameState state) {
        super(state);
    }

    @Override
    FrameEnum getFrameEnum() {
        return FrameEnum.SPARE;
    }

    @Override
    int getScore(Frame frame, Pins pins) {
        return pins.sum(
                frame.getPinsIndex(),
                offset
        );
    }

    @Override
    boolean hasScore(Frame frame, Pins pins) {
        return frame.getPinsIndex() + offset <= pins.size();
    }

    @Override
    void update(Frame frame, Pins pins) {}
}
