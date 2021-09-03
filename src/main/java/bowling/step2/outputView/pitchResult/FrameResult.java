package bowling.step2.outputView.pitchResult;

import bowling.step2.domain.Frame;
import bowling.step2.outputView.pitchResult.factory.PitchResultGroupFactory;

public class FrameResult {
    private final PitchResultGroup pitchResultGroup;

    private FrameResult(Frame frame) {
        this.pitchResultGroup = PitchResultGroupFactory.create(frame);
    }

    public static FrameResult of(Frame frame) {
        return new FrameResult(frame);
    }

    public String getPitchResults() {
        return pitchResultGroup.getPitchResults();
    }
}
