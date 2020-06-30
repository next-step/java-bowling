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

    public Frame figureOutFallenPin(FallenPinNumber fallenPinNumber) {

    }

}
