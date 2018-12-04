package domain.frames;

import domain.FrameStatus;
import domain.enums.StatusFrameEnum;
import domain.enums.StatusPointEnum;

public class NormalFrame extends Frame {

    private int frameNumber;
    private FrameStatus frameStatus;
    private StringBuilder stringBuilder;

    private NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        stringBuilder = new StringBuilder();
    }

    public static NormalFrame frameFactory(int frameNumber) {
        return new NormalFrame(frameNumber);
    }

    private NormalFrame first(int ball) {
        this.frameStatus = new FrameStatus(ball, StatusFrameEnum.FIRST);
        checkSB();
        if (askFramePoint() == StatusPointEnum.STRIKE) {
            return frameFactory(frameNumber+1);
        }

        return this;
    }

    private NormalFrame second(int ball) {
        frameStatus.statusUpdate(ball, StatusFrameEnum.SECOND);
        return frameFactory(frameNumber+1);
    }

    @Override
    public NormalFrame bowling(int ball) {
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

    public void checkSB() {
        if (askFramePoint() == StatusPointEnum.STRIKE) {
            stringBuilder.append("X");
        }

        if (askFramePoint() == StatusPointEnum.SPARE) {
            stringBuilder.append("|/");
        }

        if (askFramePoint() == StatusPointEnum.FIRSTGUTTER) {
            stringBuilder.append("-");
        }

        if (askFramePoint() == StatusPointEnum.SECONDGUTTER) {
            stringBuilder.append("|-");
        }

        if (askFramePoint() == StatusPointEnum.MISS) {
            stringBuilder.append(String.valueOf(frameStatus.getLeftPinCount()));
        }
    }

    public boolean isSameFrame(NormalFrame normalFrame) {
        NormalFrame otherNormalFrame = (NormalFrame)normalFrame;
        if (this.frameNumber == otherNormalFrame.getFrameNumber()) {
            return true;
        }
        return false;
    }
}
