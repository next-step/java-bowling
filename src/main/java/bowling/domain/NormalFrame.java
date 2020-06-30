package bowling.domain;

public class NormalFrame implements Frame {

    private FrameNumber frameNumber;
    private Pitching pitching;
    private Frame nextFrame;

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.pitching = FirstPitching.init();
    }

    public static NormalFrame of(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    public Frame figureOutFrame(FallenPinNumber fallenPinNumber) {
        if (fallenPinNumber.isStrike()) {
            pitching = StrikePitching.of(fallenPinNumber);
            nextFrame = generateNextFrame();
            return nextFrame;
        }

        return this;
    }

    private Frame generateNextFrame() {
        if (frameNumber.isNextFinalFrameNumber()) {
            return FinalFrame.of(frameNumber.nextFrameNumber());
        }

        return NormalFrame.of(frameNumber.nextFrameNumber());
    }

}
