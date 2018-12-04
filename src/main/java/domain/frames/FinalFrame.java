package domain.frames;

import domain.FrameStatus;
import domain.enums.StatusFrameEnum;

public class FinalFrame extends Frame {

    private FrameStatus frameStatus;

    private Frame first(int ball) {
        this.frameStatus = new FrameStatus(ball, StatusFrameEnum.FIRST);
        if (frameStatus.isStrike()) {
            return frameFactory(frameNumber+1);
        }
        return this;
    }

    private Frame second(int ball) {
        frameStatus.statusUpdate(ball, StatusFrameEnum.SECOND);
        if (frameStatus.isSpare()) {

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

    @Override
    public boolean isSameFrame(Frame nextNormalFrame) {
        return false;
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public int getFrameNumber() {
        return 0;
    }
}
