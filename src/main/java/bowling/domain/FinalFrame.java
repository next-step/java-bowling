package bowling.domain;

import bowling.domain.state.Start;
import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;

public class FinalFrame implements Frame {
    private final FrameInfo frameInfo;
    private State state;

    private FinalFrame(FrameInfo frameInfo) {
        validateFinalFrameNo(frameInfo);
        this.frameInfo = frameInfo;
        this.state = new Start();
    }

    public static Frame create(FrameInfo frameInfo) {
        return new FinalFrame(frameInfo);
    }

    @Override
    public void run(PitchNumberStrategy numberStrategy) {

    }

    @Override
    public Frame next() {
        throw new IllegalArgumentException("마지막 프레임입니다.");
    }

    @Override
    public FrameInfo info() {
        return frameInfo;
    }

    @Override
    public void changeState(State state) {
        this.state = state;
    }

    private void validateFinalFrameNo(FrameInfo frameInfo) {
        if (!frameInfo.last()) {
            throw new IllegalArgumentException("마지막 프레임을 생성할 수 없습니다.");
        }
    }
}
