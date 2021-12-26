package bowling.domain.state;

import bowling.annotations.ForUI;
import bowling.domain.KnockedPinCount;
import bowling.domain.PinCounts;
import bowling.domain.Score;

public class Bonus extends AbstractFinished {
    private static final int SPARE_BONUS_COUNT = 1;
    private static final int STRIKE_BONUS_COUNT = 2;

    private final int bonusCount;
    private final State previous;

    public Bonus(PinCounts pinCounts, int bonusCount, State state) {
        super(pinCounts);
        this.bonusCount = bonusCount;
        this.previous = state;
    }

    public static Bonus ofSpare(PinCounts pinCounts, Spare spare) {
        return new Bonus(pinCounts, SPARE_BONUS_COUNT - ONE, spare);
    }

    public static Bonus ofStrike(PinCounts pinCounts, Strike strike) {
        return new Bonus(pinCounts, STRIKE_BONUS_COUNT - ONE, strike);
    }

    @Override
    public State bowl(int pinCount) {
        return new Bonus(pinCounts.knockOut(pinCount), bonusCount - ONE, previous);
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
