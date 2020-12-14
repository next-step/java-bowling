package bowling.domain.frame;

class SpareFrameState extends FrameState {
    SpareFrameState(FrameState state) {
        super(state);
    }

    @Override
    int getOffset() {
        return 3;
    }

    @Override
    FrameEnum getFrameEnum() {
        return FrameEnum.SPARE;
    }

    @Override
    void updateState(Frame frame) {}
}
