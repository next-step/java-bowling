package bowling.domain.frame;

import bowling.domain.pitch.Pitches;
import bowling.exception.UnableBowlingException;
import bowling.exception.UnableCreateFrameException;

public class FinalFrame implements Frame {

    private static final int MIN_PITCHES_SIZE = 2;
    private static final int MAX_PITCHES_SIZE = 3;

    private boolean isBonus = false;
    private final Pitches pitches;

    private FinalFrame(int count) {
        this.pitches = Pitches.first(count);
    }

    protected static FinalFrame lastBowling(int count) {
        return new FinalFrame(count);
    }

    @Override
    public Frame bowling(int count) {
        if (this.isFinishBowling()) {
            throw new UnableBowlingException();
        }
        this.pitches.next(count);
        if (this.pitches.isStrikeOrSpare()) {
            this.isBonus = true;
        }
        return this;
    }

    @Override
    public Frame next(int count) {
        if (this.isFinishBowling()) {
            return this.bowling(count);
        }

        throw new UnableCreateFrameException();
    }

    @Override
    public int totalCount() {
        return this.pitches.totalCount();
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public boolean isFinishBowling() {
        if (this.isBonus) {
            return this.pitches.size() == MAX_PITCHES_SIZE;
        }
        return this.pitches.size() == MIN_PITCHES_SIZE;
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "isBonusFrame=" + isBonus +
                ", pitches=" + pitches +
                '}';
    }
}
