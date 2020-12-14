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
    FrameEnum getFrameEnum() {
        return FrameEnum.MISS;
    }

    @Override
    void updateState(Frame frame) {}
}
