package bowling.domain.frame;

import bowling.domain.bowling.BowlingScore;
import bowling.domain.pitch.Pitches;

public class NormalFrame implements Frame {
    private static final int FIRST_INDEX = 1;
    private static final int NEXT_INDEX = 1;
    private static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    private final int index;
    private final Pitches pitches = new Pitches();

    private NormalFrame(int index) {
        this.index = index;
    }

    public static Frame initiate() {
        return new NormalFrame(FIRST_INDEX);
    }

    @Override
    public Frame next() {
        if (!pitches.isMovableToNextFrame()) {
            return this;
        }
        return index == MAXIMUM_NORMAL_FRAME_INDEX ?
                FinalFrame.last(index + NEXT_INDEX) : new NormalFrame(index + NEXT_INDEX);
    }

    @Override
    public void bowl(int hitCounts) {
        pitches.recordPitch(hitCounts);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Pitches getPitches() {
        return pitches;
    }

    @Override
    public BowlingScore getScore() {
        return new BowlingScore(pitches);
    }
}
