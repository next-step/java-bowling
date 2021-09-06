package bowling.step2.domain.pitchresult;

import bowling.step2.domain.Frame;

public class FrameResult {
    private final PitchResultGroup pitchResultGroup;

    private FrameResult(Frame frame) {
        this.pitchResultGroup = frame.createResult();
    }

    public static FrameResult of(Frame frame) {
        return new FrameResult(frame);
    }

    public String getPitchResults() {
        return pitchResultGroup.getPitchResults();
    }
}
