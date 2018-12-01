package domain;

import domain.enums.StatusFrameEnum;
import domain.enums.StatusPointEnum;

public class NormalFrame {

    private int frameNumber;
    private FrameStatus frameStatus;

    private NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static NormalFrame frameFactory(int frameNumber) {
        return new NormalFrame(frameNumber);
    }

    public NormalFrame first(int ball) {
        this.frameStatus = new FrameStatus(ball, StatusFrameEnum.FIRST);

        if (askFramePoint() == StatusPointEnum.STRIKE) {
            return frameFactory(frameNumber+1);
        }

        return this;
    }

    public NormalFrame second(int ball) {
        frameStatus.statusUpdate(ball, StatusFrameEnum.SECOND);
        return frameFactory(++frameNumber);
    }

    public StatusPointEnum askFramePoint() {
        return frameStatus.getFramePoint();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (askFramePoint() == StatusPointEnum.STRIKE) {
            stringBuilder.append("X");
        }

        return stringBuilder.toString();
    }
}
