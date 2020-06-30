package bowling.domain;

public class NormalFrame implements Frame {

    private FrameNumber frameNumber;
    private FallenPinNumber firstFallenPinNumber;
    private FallenPinNumber secondFallenPinNumber;

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static NormalFrame of(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

}
