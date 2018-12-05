package domain.frames;

import domain.FrameStatus;
import domain.enums.FrameNumberEnum;
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

            if (FrameNumberEnum.FINAL_FRAME_NUMBER.isEqual(getNextFrameNumber())) {
                return new FinalFrame(getNextFrameNumber());
            }

            return frameFactory(getNextFrameNumber());
        }
        return this;
    }

    private int getNextFrameNumber() {
        return FrameNumberEnum.NEXT_DEFAULT_NUMBER.addFrameNumber(frameNumber);
    }

    private Frame second(int ball) {
        frameStatus.statusUpdate(ball, StatusFrameEnum.SECOND);

        if (FrameNumberEnum.FINAL_FRAME_NUMBER.isEqual(getNextFrameNumber())) {
            return new FinalFrame(getNextFrameNumber());
        }

        return frameFactory(getNextFrameNumber());
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

    public String showResultFirst() {
        return frameStatus.getCurrentResultFirst();
    }

    public String showResultSecond() {
        return frameStatus.getCurrentResultSecond();
    }

    public boolean isSameFrame(Frame normalFrame) {
        if (normalFrame instanceof FinalFrame) {
            return false;
        }

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
