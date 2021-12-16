package bowling.domain;

import bowling.domain.state.Start;
import bowling.domain.state.State;

public class FinalFrame extends TemplateFrame {

    private FinalFrame(FrameInfo frameInfo) {
        super(frameInfo, new Start());
    }

    public static Frame create(FrameInfo frameInfo) {
        validateFinalFrameNo(frameInfo);
        return new FinalFrame(frameInfo);
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

    @Override
    public State state() {
        return state;
    }

    private static void validateFinalFrameNo(FrameInfo frameInfo) {
        if (!frameInfo.last()) {
            throw new IllegalArgumentException("마지막 프레임을 생성할 수 없습니다.");
        }
    }
}
