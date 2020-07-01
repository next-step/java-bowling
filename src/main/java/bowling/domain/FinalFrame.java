package bowling.domain;

public class FinalFrame implements Frame {

    private FrameNumber frameNumber;
    private Pitching pitching;

    private FinalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.pitching = new StandbyPitching();
    }

    public static FinalFrame of(FrameNumber frameNumber) {
        return new FinalFrame(frameNumber);
    }

    @Override
    public Frame figureOutFrame(FallenPinNumber fallenPinNumber) {
        Pitching action = pitching.pitch(fallenPinNumber);
        if (pitching.isFinished(this)) {
            return this;
        }

        return null;
    }
}
