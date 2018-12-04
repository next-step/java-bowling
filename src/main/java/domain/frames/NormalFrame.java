package domain.frames;

import domain.FrameStatus;
import domain.enums.StatusFrameEnum;
import domain.enums.StatusPointEnum;

public class NormalFrame extends Frame {

    private int frameNumber;
    private FrameStatus frameStatus;

    private NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static NormalFrame frameFactory(int frameNumber) {
        return new NormalFrame(frameNumber);
    }

    private Frame first(int ball) {
        this.frameStatus = new FrameStatus(ball, StatusFrameEnum.FIRST);
        if (frameStatus.isStrike()) {

            if (frameNumber +1 == 10) {
                return new FinalFrame();
            }

            return frameFactory(frameNumber+1);
        }
        return this;
    }

    private Frame second(int ball) {
        frameStatus.statusUpdate(ball, StatusFrameEnum.SECOND);

        if (frameNumber +1 == 10) {
            return new FinalFrame();
        }

        return frameFactory(frameNumber+1);
    }

    @Override
    public Frame bowling(int ball) {
        if (this.frameStatus == null) {
            return this.first(ball);
        }
        return this.second(ball);
    }

    public StatusPointEnum askFramePoint() {
        return frameStatus.getFramePoint();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public String showResultFirst(){
        return frameStatus.getCurrentResultFirst();
    }

    public String showResultSecond(){
        return frameStatus.getCurrentResultSecond();
    }

    public boolean isSameFrame(Frame normalFrame) {
        NormalFrame otherNormalFrame = (NormalFrame)normalFrame;
        if (this.frameNumber == otherNormalFrame.getFrameNumber()) {
            return true;
        }
        return false;
    }

    public boolean isStrike() {
        return this.frameStatus.isStrike();
    }
}
