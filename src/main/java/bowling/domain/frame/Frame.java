package bowling.domain.frame;

import bowling.domain.bowling.BowlingPinsGroup;

public interface Frame {
    public static final int FIRST_INDEX = 1;
    public static final int NEXT_INDEX = 1;
    public static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    public Frame next();

    public void bowl(int hitCounts, BowlingPinsGroup bowlingPinsGroup);

    public int getIndex();
}
