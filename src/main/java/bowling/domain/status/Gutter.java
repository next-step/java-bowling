package bowling.domain.status;

import bowling.domain.Pitches;

public class Gutter implements Status {
    @Override
    public boolean condition(Pitches pitches) {
        return false;
    }

    @Override
    public boolean conditionOf(int fallenPins, int accumulatedPins, int pitchIndex) {
        return fallenPins == 0;
    }

    @Override
    public String display(Pitches pitches) {
        return null;
    }

    @Override
    public String display(int fallenPins) {
        return "-";
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
