package bowling.domain.frame;

import bowling.domain.frame.exception.UnableBowlingException;
import bowling.domain.frame.exception.UnableCreateFrameException;
import bowling.domain.pitch.Pitches;

public class FinalFrame implements Frame {

    private static final int MIN_PITCHES_SIZE = 2;
    private static final int MAX_PITCHES_SIZE = 3;
    private static final int FINAL_ROUND = 9;

    private boolean isBonus = false;
    private final Pitches pitches;

    private FinalFrame(int pins) {
        this.pitches = Pitches.first(pins);
    }

    protected static FinalFrame lastBowling(int pins) {
        return new FinalFrame(pins);
    }

    @Override
    public Frame bowling(int pins) {
        if (this.isFinishBowling()) {
            throw new UnableBowlingException();
        }

        if (this.pitches.isStrikeOrSpare()) {
            this.pitches.bonus(pins);
            this.isBonus = true;
            return this;
        }

        this.pitches.next(pins);

        return this;
    }

    @Override
    public Frame next(int pins) {
        if (!this.isFinishBowling()) {
            return this.bowling(pins);
        }

        throw new UnableCreateFrameException();
    }

    @Override
    public int round() {
        return FINAL_ROUND;
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
    public String partitionPins() {
        String currentScore = this.pitches.currentScore();
        if (this.isBonus) {
            currentScore += ("|" + this.pitches.bonusPitch().pins());
        }
        return currentScore;
    }
}
