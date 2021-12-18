package bowling.domain;

import bowling.domain.state.End;
import bowling.domain.state.Progress;
import bowling.domain.state.Start;
import bowling.domain.state.State;

public class FinalFrame extends TemplateFrame {

    private FinalFrame() {
        super(FrameInfo.create(9), new Start());
    }

    private FinalFrame(FrameInfo frameInfo) {
        super(frameInfo, new Start());
    }

    public static Frame create() {
        return new FinalFrame();
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
    public void changeState() {
        if (isStrike() || isSpare()) {
            checkPitchNo();
            return;
        }
        checkProgress();
    }

    private void checkProgress() {
        if (state instanceof Progress) {
            checkRetryable();
            return;
        }
        changeState(new Progress(false));
    }

    private void checkRetryable() {
        if (retryable()) {
            checkPitchNo();
            return;
        }
        changeState(new End());
    }

    private void checkPitchNo() {
        if (isSecondPitch()) {
            changeState(new Progress());
            return;
        }
        checkBonusPitchNo();
    }

    private void checkBonusPitchNo() {
        if (isThirdPitch()) {
            changeState(new End());
            return;
        }
        changeState(new Progress(true));
    }

    private boolean retryable() {
        return state.retryable();
    }

    private boolean isSecondPitch() {
        return frameInfo.isSecondPitch();
    }

    private boolean isThirdPitch() {
        return frameInfo.isThirdPitch();
    }

    private void changeState(State state) {
        this.state = state;
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public boolean isStrike() {
        return frameInfo.isStrike();
    }

    @Override
    public boolean isSpare() {
        return frameInfo.isSpare();
    }

    private static void validateFinalFrameNo(FrameInfo frameInfo) {
        if (!frameInfo.last()) {
            throw new IllegalArgumentException("마지막 프레임을 생성할 수 없습니다.");
        }
    }
}
