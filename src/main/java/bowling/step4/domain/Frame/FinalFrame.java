package bowling.step4.domain.Frame;

import bowling.step4.domain.Pitches;

public class FinalFrame implements Frame {
    private static final int BONUS_PITCH_CHANCE = 3;
    private static final int DEFAULT_PITCH_CHANCE = 2;

    private final Pitches pitches;

    public FinalFrame() {
        this.pitches = new Pitches();
    }

    @Override
    public Boolean isEndedFrame() {
        if (hasBonusCondition() && this.pitches.getSize() == BONUS_PITCH_CHANCE) {
            return true;
        }
        return !hasBonusCondition() && this.pitches.getSize() == DEFAULT_PITCH_CHANCE;
    }

    @Override
    public Boolean isFinalFrame() {
        return true;
    }

    @Override
    public void add(int count) {
        pitches.add(count);
    }

    @Override
    public int firstPitch() {
        return this.pitches.firstPitch();
    }

    @Override
    public int secondPitch() {
        return this.pitches.secondPitch();
    }

    @Override
    public Pitches pitches() {
        return this.pitches;
    }

    private Boolean hasBonusCondition() {
        return this.pitches.hasStrike() || this.pitches.hasSpare();
    }
}
