package bowling.step2.domain.pitchresult.factory;

import bowling.step2.domain.Frame;
import bowling.step2.domain.NormalFrame;
import bowling.step2.domain.pitchresult.LastFramePitchResultGroup;
import bowling.step2.domain.pitchresult.NormalFramePitchResultGroup;
import bowling.step2.domain.pitchresult.PitchResultGroup;

public class PitchResultGroupFactory {
    public static PitchResultGroup create(Frame frame) {
        if (frame instanceof NormalFrame) {
            return NormalFramePitchResultGroup.of(frame);
        }

        return LastFramePitchResultGroup.of(frame);
    }
}
