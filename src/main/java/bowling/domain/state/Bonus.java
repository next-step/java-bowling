package bowling.domain.state;

import bowling.annotations.ForUI;
import bowling.domain.KnockedPinCounts;

public class Bonus extends AbstractFinished {
    private static final int SPARE_BONUS_COUNT = 1;
    private static final int STRIKE_BONUS_COUNT = 2;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    private final int bonusCount;
    private final State previous;

    public Bonus(int bonusCount, State state) {
        this.bonusCount = bonusCount;
        this.previous = state;
    }

    public Bonus(KnockedPinCounts knockedPinCounts, int bonusCount, State state) {
        super(knockedPinCounts);
        this.bonusCount = bonusCount;
        this.previous = state;
    }

    public static Bonus ofSpare(KnockedPinCounts knockedPinCounts, Spare spare) {
        return new Bonus(knockedPinCounts, SPARE_BONUS_COUNT - ONE, spare);
    }

    public static Bonus ofStrike(KnockedPinCounts knockedPinCounts, Strike strike) {
        return new Bonus(knockedPinCounts, STRIKE_BONUS_COUNT - ONE, strike);
    }

    @Override
    public State bowl(int pinCount) {
        return new Bonus(knockedPinCounts.knockOut(pinCount), bonusCount - ONE, previous);
    }

    @Override
    public boolean isFinished() {
        return bonusCount == ZERO;
    }

    @Override
    public boolean hasBonus() {
        return false;
    }

    @ForUI
    public State getPrevious() {
        return previous;
    }
}
