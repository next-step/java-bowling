package bowling.domain.frame;

import bowling.domain.pitch.Pitches;
import bowling.exception.UnableBowlingException;
import bowling.exception.UnableCreateFrameException;

public class FinalFrame implements Frame {

    private static final int MIN_PITCHES_SIZE = 2;
    private static final int MAX_PITCHES_SIZE = 3;
    private static final int FINAL_ROUND = 9;

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

        if (this.pitches.isStrikeOrSpare()) {
            this.pitches.bonus(count);
            this.isBonus = true;
            return this;
        }

        this.pitches.next(count);

        return this;
    }

    @Override
    public Frame next(int count) {
        if (!this.isFinishBowling()) {
            return this.bowling(count);
        }

        throw new UnableCreateFrameException();
    }

    @Override
    public int round() {
        return FINAL_ROUND;
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
        if (pitches.isSpare()) {
            return isBonus && this.pitches.size() == MAX_PITCHES_SIZE;
        }

        if (pitches.isStrike()) {
            return isBonus && this.pitches.size() == MIN_PITCHES_SIZE;
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
