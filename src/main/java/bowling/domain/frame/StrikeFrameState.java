package bowling.domain.frame;

class StrikeFrameState extends FrameState {
    StrikeFrameState(FrameState state) {
        super(state);
    }

    @Override
    int getOffset() {
        return 3;
    }

    @Override
    FrameEnum getFrameEnum() {
        return FrameEnum.STRIKE;
    }

    @Override
    void updateState(Frame frame) {}
}
