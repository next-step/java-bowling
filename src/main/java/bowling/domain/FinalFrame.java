package bowling.domain;

public class FinalFrame implements Frame {

    private FrameNumber frameNumber;
    private Pitching pitching;

    private FinalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static FinalFrame of(FrameNumber frameNumber) {
        return new FinalFrame(frameNumber);
    }


}
