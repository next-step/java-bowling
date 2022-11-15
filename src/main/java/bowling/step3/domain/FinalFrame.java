package bowling.step3.domain;

public class FinalFrame extends Frame {
    private static final int BONUS_PITCH_CHANCE = 3;
    private static final int DEFAULT_PITCH_CHANCE = 3;

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

    private Boolean hasBonusCondition() {
        return this.pitches.hasStrike() || this.pitches.hasSpare();
    }
}
