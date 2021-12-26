package bowling.domain.state;

import bowling.annotations.ForUI;
import bowling.domain.KnockedPinCount;
import bowling.domain.KnockedPinCounts;
import bowling.domain.Score;

public class Bonus extends AbstractFinished {
    private static final int SPARE_BONUS_COUNT = 1;
    private static final int STRIKE_BONUS_COUNT = 2;

    private final int bonusCount;
    private final State previous;

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

    @Override
    public Score makeScore() {
        int sum = getValues().stream().map(KnockedPinCount::value)
                .reduce(ZERO, Integer::sum)
                .intValue();
        return new Score(sum, ZERO);
    }

    @Override
    public Score additionalCalculate(Score beforeScore) {
        Score score = beforeScore.bowl(getValues().get(ZERO).value());
        if (score.canCalculateScore()) {
            return score;
        }
        return score.bowl(getValues().get(ONE).value());
    }

    @ForUI
    public State getPrevious() {
        return previous;
    }
}
