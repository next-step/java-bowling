package bowling.domain.frame;

import bowling.domain.bowling.BowlingPinsGroup;
import bowling.domain.pitch.FinalPitches;

public class FinalFrame implements Frame {

    private final int index;
    private final FinalPitches finalPitches = new FinalPitches();

    private FinalFrame(int index) {
        this.index = index;
    }

    public static FinalFrame last(int index) {
        return new FinalFrame(index);
    }

    @Override
    public boolean isRequestingNewBowlingPinsGroup() {
        return finalPitches.getPitchesSum() % 10 == 0;
    }

    @Override
    public boolean isMovableToNextFrame() {
        if (finalPitches.getPitchCounts() < 2) {
            return false;
        }
        if (finalPitches.getPitchCounts() == 2 && finalPitches.isAvailableToPitchBonus()) {
            return false;
        }
        return true;
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public void bowl(int hitCounts, BowlingPinsGroup bowlingPinsGroup) {
        finalPitches.throwBall(hitCounts, bowlingPinsGroup);

    }

    @Override
    public int getIndex() {
        return index;
    }
}
