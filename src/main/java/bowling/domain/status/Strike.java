package bowling.domain.status;

import bowling.domain.Pitches;

public class Strike extends Finish {
    private static final int PINS = 10;
    private static final int PITCH_INDEX = 1;

    @Override
    public boolean condition(Pitches pitches) {
        return pitches.sum() == PINS && pitches.count() == PITCH_INDEX;
    }

    @Override
    public boolean conditionOf(int fallenPins, int accumulatedPins, int pitchIndex) {
        return fallenPins == PINS && pitchIndex == PITCH_INDEX;
    }

    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public String display(Pitches pitches) {
        return "X";
    }

    @Override
    public String display(int fallenPins) {
        return "X";
    }
}
