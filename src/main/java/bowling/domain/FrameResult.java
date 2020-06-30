package bowling.domain;

public class FrameResult {

    private FrameNumber frameNumber;
    private PitchingResultDescription pitchingResultDescription;

    private FrameResult(FrameNumber frameNumber, PitchingResultDescription pitchingResultDescription) {
        this.frameNumber = frameNumber;
        this.pitchingResultDescription = pitchingResultDescription;
    }

    public static FrameResult of(FrameNumber frameNumber, PitchingResultDescription pitchingResultDescription) {
        return new FrameResult(frameNumber, pitchingResultDescription);
    }
}
