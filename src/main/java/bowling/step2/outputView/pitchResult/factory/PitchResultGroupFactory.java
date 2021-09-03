package bowling.step2.outputView.pitchResult.factory;

import bowling.step2.domain.Frame;
import bowling.step2.domain.NormalFrame;
import bowling.step2.outputView.pitchResult.LastFramePitchResultGroup;
import bowling.step2.outputView.pitchResult.NormalFramePitchResultGroup;
import bowling.step2.outputView.pitchResult.PitchResultGroup;

public class PitchResultGroupFactory {
    public static PitchResultGroup create(Frame frame) {
        if (frame instanceof NormalFrame) {
            return NormalFramePitchResultGroup.of(frame);
        }

        return LastFramePitchResultGroup.of(frame);
    }
}
