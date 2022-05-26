package bowling.domain.frame;

import bowling.domain.pitch.Pitches;
import bowling.exception.UnableBowlingException;
import bowling.exception.UnableCreateFrameException;

public class FinalFrame implements Frame {

    private final boolean isBonusFrame;
    private final Pitches pitches;

    private FinalFrame(boolean isBonusFrame, int count) {
        this.isBonusFrame = isBonusFrame;
        this.pitches = Pitches.first(count);
    }

    protected static FinalFrame lastBowling(int count) {
        return new FinalFrame(false, count);
    }

    private static FinalFrame bonusBowling(int count) {
        return new FinalFrame(true, count);
    }

    @Override
    public Frame bowling(int count) {
        if (this.isBonusFrame) {
            throw new UnableBowlingException();
        }
        this.pitches.next(count);
        return this;
    }

    @Override
    public Frame next(int count) {
        if (!this.isBonusFrame && this.pitches.isBonus()) {
            return bonusBowling(count);
        }
        throw new UnableCreateFrameException();
    }

    @Override
    public int totalCount() {
        return this.pitches.totalCount();
    }

    @Override
    public boolean isFinal() {
        return this.isBonusFrame;
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "isBonusFrame=" + isBonusFrame +
                ", pitches=" + pitches +
                '}';
    }
}
