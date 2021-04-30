package bowling.domain.status;

import bowling.domain.Pitches;

import java.util.ArrayList;
import java.util.List;

public class Bonus extends Symbol implements Status {
    @Override
    public boolean condition(Pitches pitches) {
        return pitches.count() == 3;
    }

    @Override
    public boolean conditionOf(int fallenPins, int accumulatedPins, int pitchIndex) {
        return pitchIndex == 3;
    }

    @Override
    public String display(Pitches pitches) {
        List<String> result = new ArrayList<>();

        return String.join(DELIMITER, result);
    }

    @Override
    public String display(int fallenPins) {
        return "";
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
