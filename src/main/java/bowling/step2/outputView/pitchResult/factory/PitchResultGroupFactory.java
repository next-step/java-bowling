package bowling.step2.outputView.pitchResult.factory;

import bowling.step2.domain.Frame;
import bowling.step2.domain.NormalFrame;
import bowling.step2.outputView.pitchResult.LastPitchResultGroup;
import bowling.step2.outputView.pitchResult.NormalPitchResultGroup;
import bowling.step2.outputView.pitchResult.PitchResultGroup;

public class PitchResultGroupFactory {
    public static PitchResultGroup create(Frame frame) {
        if (frame instanceof NormalFrame) {
            return NormalPitchResultGroup.of(frame);
        }

        return LastPitchResultGroup.of(frame);
    }
}
