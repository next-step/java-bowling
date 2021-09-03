package bowling.step2.outputView.pitchResult;

import bowling.step2.domain.Frame;
import bowling.step2.outputView.state.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalPitchResultGroup implements PitchResultGroup{
    private final List<PitchResult> pitchResults;

    private NormalPitchResultGroup(List<PitchResult> pitchResults) {
        this.pitchResults = pitchResults;
    }

    public static NormalPitchResultGroup of(Frame frame) {
        List<Integer> current = frame.current();

        if (current.size() == 1) {
            return new NormalPitchResultGroup(Collections.singletonList(PitchResult.of(new Strike(), current.get(0))));
        }
        List<PitchResult> temp = new ArrayList<>();

        if (current.get(0) == 0) {
            temp.add(PitchResult.of(new Gutter(), current.get(0)));
            if (current.get(1) == 0) {
                temp.add(PitchResult.of(new Gutter(), current.get(1)));
            } else if (current.get(1) == 10) {
                temp.add(PitchResult.of(new Spare(), current.get(1)));
            } else {
                temp.add(PitchResult.of(new Miss(), current.get(1)));
            }

            return new NormalPitchResultGroup(temp);
        }

        temp.add(PitchResult.of(new Miss(), current.get(0)));
        if (current.get(1) == 0) {
            temp.add(PitchResult.of(new Gutter(), current.get(1)));
        } else if (current.get(0) + current.get(1) == 10) {
            temp.add(PitchResult.of(new Spare(), current.get(1)));
        } else {
            temp.add(PitchResult.of(new Miss(), current.get(1)));
        }

        return new NormalPitchResultGroup(temp);
    }

    public List<PitchResult> getPitchResults() {
        return pitchResults;
    }
}
