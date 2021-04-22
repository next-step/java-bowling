package bowling.domain.engine;

import java.util.ArrayList;
import java.util.List;

public class Records {

    private final List<PitchResult> results = new ArrayList<>();

    public void add(PitchResult pitchResult) {
        results.add(pitchResult);
    }

    public boolean isStrike() {
        return !results.isEmpty() && results.get(0).getValue() == Pins.MAXIMUM_PINS;
    }

    public boolean isSpare() {
        return results.size() == 2 && results.stream().mapToInt(PitchResult::getValue).sum() == Pins.MAXIMUM_PINS;
    }

    public boolean isMissed() {
        return results.size() == 2 && results.stream().mapToInt(PitchResult::getValue).sum() < Pins.MAXIMUM_PINS;
    }

    public int throwCounts() {
        return results.size();
    }

}
