package bowling.domain.frame;

import bowling.domain.bowling.BowlingPinsGroup;
import bowling.domain.pitch.NormalPitches;

public class NormalFrame implements Frame {

    private final int index;
    private final NormalPitches normalPitches = new NormalPitches();

    private NormalFrame(int index) {
        this.index = index;
    }

    public static Frame initiate() {
        return new NormalFrame(FIRST_INDEX);
    }

    @Override
    public Frame next() {
        return index == MAXIMUM_NORMAL_FRAME_INDEX ?
                FinalFrame.last(index + NEXT_INDEX) : new NormalFrame(index + NEXT_INDEX);
    }

    @Override
    public boolean isMovableToNextFrame() {
        return normalPitches.getPitchCounts() == MAXIMUM_NORMAL_PITCH_COUNTS || normalPitches.isStrike();
    }

    @Override
    public boolean isRequestingNewBowlingPinsGroup() {
        return normalPitches.getPitchCounts() == 0;
    }

    @Override
    public void bowl(int hitCounts, BowlingPinsGroup bowlingPinsGroup) {
        normalPitches.throwBall(hitCounts, bowlingPinsGroup);
    }

    public int getIndex() {
        return index;
    }
}
