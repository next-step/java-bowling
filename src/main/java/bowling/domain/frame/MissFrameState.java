package bowling.domain.frame;

class MissFrameState extends FrameState {
    MissFrameState(FrameState state) {
        super(state);
    }

    @Override
    int getOffset() {
        return 2;
    }

    @Override
    FrameStatus getFrameStatus() {
        return FrameStatus.MISS;
    }

    @Override
    void updateState(Frame frame) {}
}
