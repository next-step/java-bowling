package bowling.domain;

public class NormalFrame implements Frame {

    private FrameNumber frameNumber;
    private Pitching pitching;
    private Frame nextFrame;

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static NormalFrame of(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    public Frame figureOutFrameFromFirstPitching(FirstPitching firstPitching) {
        if (firstPitching.isStrike()) {
            nextFrame = generateFrame();
            return nextFrame;
        }

        return this;
    }

    private Frame generateFrame() {
        if (frameNumber.isNextFinalFrameNumber()) {
            return FinalFrame.of(frameNumber.nextFrameNumber());
        }

        return NormalFrame.of(frameNumber.nextFrameNumber());
    }

}
