package domain.frames;

import domain.FrameStatus;
import domain.enums.FrameNumberEnum;
import domain.enums.StatusFrameEnum;
import domain.status.Status;

public class NormalFrame implements Frame {

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

    public Status askFramePoint() {
        return frameStatus.getFramePoint();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public Status collectResult() {
        return frameStatus.getFramePoint();
    }

}
