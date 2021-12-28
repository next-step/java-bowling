package bowling.domain.state;

import bowling.domain.KnockedPinCount;
import bowling.domain.PinCounts;
import bowling.domain.Score;

public class Bonus extends AbstractFinished {
    private static final int SPARE_BONUS_COUNT = 1;
    private static final int STRIKE_BONUS_COUNT = 2;

    private final int bonusCount;

    private Bonus(PinCounts pinCounts, int bonusCount) {
        super(pinCounts);
        this.bonusCount = bonusCount;
    }

    public static Bonus ofSpare(PinCounts pinCounts) {
        return new Bonus(pinCounts, SPARE_BONUS_COUNT - ONE);
    }

    public static Bonus ofStrike(PinCounts pinCounts) {
        return new Bonus(pinCounts, STRIKE_BONUS_COUNT - ONE);
    }

    @Override
    public State bowl(int pinCount) {
        return new Bonus(pinCounts.knockOut(pinCount), bonusCount - ONE);
    }

    @Override
    public boolean isEnd() {
        return bonusCount == ZERO;
    }

    @Override
    public boolean hasBonus() {
        return false;
    }

    @Override
    public Score makeScore() {
        int sum = getValues().stream()
                .map(KnockedPinCount::value)
                .reduce(ZERO, Integer::sum);
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
}
