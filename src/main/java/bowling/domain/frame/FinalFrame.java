package bowling.domain.frame;

import bowling.domain.bowling.BowlingScoreParser;
import bowling.domain.pitch.Pitches;

public class FinalFrame implements Frame {

    private final int index;
    private final Pitches pitches = new Pitches();

    private FinalFrame(int index) {
        this.index = index;
    }

    public static FinalFrame last(int index) {
        return new FinalFrame(index);
    }

    @Override
    public Frame next() {
        return pitches.isMovableToNextPitch() ? this : null;
    }

    @Override
    public void bowl(int hitCounts) {
        pitches.recordPitch(hitCounts);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Pitches getPitches() {
        return pitches;
    }

    @Override
    public String getScore() {
        return BowlingScoreParser.parse(pitches);
    }
}
