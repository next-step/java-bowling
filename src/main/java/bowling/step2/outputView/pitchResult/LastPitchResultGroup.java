package bowling.step2.outputView.pitchResult;

import bowling.step2.domain.Frame;
import bowling.step2.outputView.state.Gutter;
import bowling.step2.outputView.state.Miss;
import bowling.step2.outputView.state.Spare;
import bowling.step2.outputView.state.Strike;

import java.util.ArrayList;
import java.util.List;

public class LastPitchResultGroup implements PitchResultGroup {
    private final List<PitchResult> pitchResults;

    private LastPitchResultGroup(List<PitchResult> pitchResults) {
        this.pitchResults = pitchResults;
    }

    public static LastPitchResultGroup of(Frame frame) {
        List<Integer> current = frame.current();

        List<PitchResult> temp = new ArrayList<>();

        if (current.get(0) == 0) {
            temp.add(PitchResult.of(new Gutter(), current.get(0)));
        } else if (current.get(0) == 10) {
            temp.add(PitchResult.of(new Strike(), current.get(0)));
        } else {
            temp.add(PitchResult.of(new Miss(), current.get(0)));
        }

        if (current.get(1) == 0) {
            temp.add(PitchResult.of(new Gutter(), current.get(1)));
        } else if (current.get(0) + current.get(1) == 10) {
            temp.add(PitchResult.of(new Spare(), current.get(1)));
        } else if (current.get(0) + current.get(1) == 20) {
            temp.add(PitchResult.of(new Strike(), current.get(1)));
        } else {
            temp.add(PitchResult.of(new Miss(), current.get(1)));
        }

        if (current.size() == 2) {
            return new LastPitchResultGroup(temp);
        }

        if (current.get(2) == 0) {
            temp.add(PitchResult.of(new Gutter(), current.get(2)));
        } else if (current.get(2) == 10) {
            temp.add(PitchResult.of(new Strike(), current.get(2)));
        } else if (current.get(1) + current.get(2) == 10) {
            temp.add(PitchResult.of(new Spare(), current.get(2)));
        } else {
            temp.add(PitchResult.of(new Miss(), current.get(2)));
        }

        return new LastPitchResultGroup(temp);
    }

    public List<PitchResult> getPitchResults() {
        return pitchResults;
    }
}
