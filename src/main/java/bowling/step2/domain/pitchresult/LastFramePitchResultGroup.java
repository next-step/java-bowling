package bowling.step2.domain.pitchresult;

import bowling.step2.domain.Frame;
import bowling.step2.domain.state.Gutter;
import bowling.step2.domain.state.Miss;
import bowling.step2.domain.state.Spare;
import bowling.step2.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFramePitchResultGroup implements PitchResultGroup {
    private final List<PitchResult> pitchResults;

    private LastFramePitchResultGroup(List<PitchResult> pitchResults) {
        this.pitchResults = pitchResults;
    }

    public static LastFramePitchResultGroup of(Frame frame) {
        List<Integer> current = frame.current();
        List<PitchResult> temp = new ArrayList<>();

        temp.add(makeFirstResult(current.get(0)));

        if (pitchedOnlyOnce(current)) {
            return new LastFramePitchResultGroup(temp);
        }

        temp.add(makeSecondResult(current.get(0), current.get(1)));

        if (pitchedJustTwice(current)) {
            return new LastFramePitchResultGroup(temp);
        }

        temp.add(makeSecondResult(current.get(1), current.get(2)));

        return new LastFramePitchResultGroup(temp);
    }

    private static boolean pitchedOnlyOnce(List<Integer> current) {
        return current.size() == 1;
    }

    private static boolean pitchedJustTwice(List<Integer> current) {
        return current.size() == 2;
    }

    private static PitchResult makeFirstResult(int count) {
        if (count == 10) {
            return PitchResult.of(new Strike(), count);
        }

        return getPitchResult(count);
    }

    private static PitchResult makeSecondResult(int prevCount, int count) {
        if (prevCount + count == 10) {
            return PitchResult.of(new Spare(), count);
        }

        if (count == 10) {
            return PitchResult.of(new Strike(), count);
        }

        return getPitchResult(count);
    }

    private static PitchResult getPitchResult(int count) {
        if (count == 0) {
            return PitchResult.of(new Gutter(), count);
        }

        return PitchResult.of(new Miss(), count);
    }

    public String getPitchResults() {
        return pitchResults.stream()
                .map(PitchResult::result)
                .collect(Collectors.joining("|"));
    }
}
