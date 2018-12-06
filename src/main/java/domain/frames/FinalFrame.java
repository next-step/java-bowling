package domain.frames;

import domain.FrameStatus;
import domain.enums.FrameNumberEnum;
import domain.enums.StatusFrameEnum;
import domain.status.Status;

public class FinalFrame implements Frame {

    private int frameNumber;
    private FrameStatus frameStatus;

    public FinalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    private Frame first(int ball) {
        this.frameStatus = new FrameStatus(ball, StatusFrameEnum.FIRST);
        if (frameStatus.isStrike()) {
            return NormalFrame.frameFactory(FrameNumberEnum.EXTRA_FRAME_NUMBER.getFrameNumber());
        }
        return this;
    }

    private Frame second(int ball) {
        frameStatus.statusUpdate(ball, StatusFrameEnum.SECOND);
        if (frameStatus.isSpare()) {
            return NormalFrame.frameFactory(FrameNumberEnum.EXTRA_FRAME_NUMBER.getFrameNumber());
        }
        return NormalFrame.frameFactory(FrameNumberEnum.FINISH_FRAME_NUMBER.getFrameNumber());
    }

    @Override
    public Frame bowling(int ball) {
        if (this.frameStatus == null) {
            return this.first(ball);
        }
        return this.second(ball);
    }

    @Override
    public int getFrameNumber() {
        return this.frameNumber;
    }

    @Override
    public Status collectResult() {
        return frameStatus.getFramePoint();
    }
}
