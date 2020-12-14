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
    FrameStatus getFrameStatus() {
        return FrameStatus.SPARE;
    }

    @Override
    void updateState(Frame frame) {}
}
