package bowling.domain;

public class NormalFrame extends Frame {

    private FrameNumber frameNumber;

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static NormalFrame of(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    public Frame getNextFrame() {
        FrameNumber nextFrameNumber = frameNumber.getNextFrameNumber();

        if (frameNumber.canGenerateNextFrame()) {
            return NormalFrame.of(nextFrameNumber);
        }
        return FinalFrame.of(nextFrameNumber);
    }
}
