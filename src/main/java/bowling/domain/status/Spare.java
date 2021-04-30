package bowling.domain.status;

import bowling.domain.Pitches;

public class Spare extends Finish {
    private static final int PINS = 10;
    private static final int PITCH_INDEX = 2;

    @Override
    public boolean condition(Pitches pitches) {
        return pitches.sum() == PINS && pitches.count() == PITCH_INDEX;
    }

    @Override
    public boolean conditionOf(int fallenPins, int accumulatedPins, int pitchIndex) {
        return accumulatedPins == PINS && pitchIndex == PITCH_INDEX;
    }

    @Override
    public String display(Pitches pitches) {
        return pitches.firstPitch() + "|" + "/";
    }

    @Override
    public String display(int fallenPins) {
        return "/";
    }

    @Override
    public boolean isSpare() {
        return true;
    }
}
