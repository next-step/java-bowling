package bowling.domain.status;

import bowling.domain.Pitches;

import java.util.ArrayList;
import java.util.List;

public class Open extends Finish {
    @Override
    public boolean condition(Pitches pitches) {
        return pitches.count() == 2 && pitches.sum() < 10;
    }

    @Override
    public boolean conditionOf(int fallenPins, int accumulatedPins, int pitchIndex) {
        return pitchIndex == 2 && accumulatedPins < 10;
    }

    @Override
    public String display(Pitches pitches) {
        List<String> result = new ArrayList<>();
        pitches.forEach(pitch -> result.add(String.valueOf(pitch.intValue())));
        return String.join("|", result);
    }

    @Override
    public String display(int fallenPins) {
        return String.valueOf(fallenPins);
    }

    @Override
    public boolean isOpen() {
        return true;
    }
}
