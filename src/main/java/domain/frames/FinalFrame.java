package domain.frames;

import domain.FrameStatus;
import domain.enums.StatusFrameEnum;

public class FinalFrame extends Frame {

    private int frameNumber;
    private FrameStatus frameStatus;

    public FinalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    private Frame first(int ball) {
        this.frameStatus = new FrameStatus(ball, StatusFrameEnum.FIRST);
        if (frameStatus.isStrike()) {
            return NormalFrame.frameFactory(11);
        }
        return this;
    }

    private Frame second(int ball) {
        frameStatus.statusUpdate(ball, StatusFrameEnum.SECOND);
        if (frameStatus.isSpare()) {
            return NormalFrame.frameFactory(11);
        }
        return NormalFrame.frameFactory(12);
    }

    @Override
    public Frame bowling(int ball) {
        if (this.frameStatus == null) {
            return this.first(ball);
        }
        return this.second(ball);
    }

    @Override
    public boolean isSameFrame(Frame frame) {
        if (frame instanceof NormalFrame) {
            return false;
        }

        FinalFrame otherNormalFrame = (FinalFrame)frame;
        if (this.frameNumber == otherNormalFrame.getFrameNumber()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isStrike() {
        return this.frameStatus.isStrike();
    }

    @Override
    public int getFrameNumber() {
        return this.frameNumber;
    }

    @Override
    public String showResultFirst() {
        return this.frameStatus.getCurrentResultFirst();
    }

    @Override
    public String showResultSecond() {
        return this.frameStatus.getCurrentResultSecond();
    }
}
