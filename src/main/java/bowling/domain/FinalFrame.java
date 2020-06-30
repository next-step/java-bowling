package bowling.domain;

public class FinalFrame implements Frame {

    private FrameNumber frameNumber;
    private FallenPinNumber firstFallenPinNumber;
    private FallenPinNumber secondFallenPinNumber;
    private FallenPinNumber finalFallenPinNumber;

    private FinalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static FinalFrame of(FrameNumber frameNumber) {
        return new FinalFrame(frameNumber);
    }


}
