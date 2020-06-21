package bowling.domain.frame;

import bowling.domain.bowling.BowlingPinsGroup;
import bowling.domain.pitch.Pitches;

public interface Frame {
    public static final int FIRST_INDEX = 1;
    public static final int NEXT_INDEX = 1;
    public static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    public Frame next();

    public void bowl(int hitCounts);

    public void bowl2(int hitCounts, BowlingPinsGroup bowlingPinsGroup);

    public int getIndex();

    public Pitches getPitches();
}
