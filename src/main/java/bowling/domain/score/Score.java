package bowling.domain.score;

import bowling.domain.state.Pins;
import bowling.exception.BonusCountNullPointerException;
import bowling.exception.InvalidScoreSizeException;

import java.util.Objects;

import static java.lang.Math.addExact;

public final class Score {

    private static final int UN_AVAILABLE_SCORE = -1;
    private static final int ALL_PIN_COUNT = 10;
    private static final int MAXIMUM_SCORE = 30;

    private final int score;
    private final BonusCount bonusCount;

    public static final Score unavailable() {
        return new Score(UN_AVAILABLE_SCORE, BonusCount.none());
    }

    public static final Score miss(final int pins) {
        return miss(Pins.valueOf(pins));
    }

    public static final Score miss(final Pins pins) {
        return new Score(pins.count(), BonusCount.none());
    }

    public static final Score spare() {
        return new Score(ALL_PIN_COUNT, BonusCount.spare());
    }

    public static final Score strike() {
        return new Score(ALL_PIN_COUNT, BonusCount.strike());
    }

    private Score(final int score, final BonusCount bonusCount) {
        validateNull(bonusCount);
        validateSize(score);
        this.score = score;
        this.bonusCount = bonusCount;
    }

    private final void validateSize(final int score) {
        if (score < UN_AVAILABLE_SCORE || score > MAXIMUM_SCORE) {
            throw new InvalidScoreSizeException();
        }
    }

    private final void validateNull(final BonusCount bonusCount) {
        if (Objects.isNull(bonusCount)) {
            throw new BonusCountNullPointerException();
        }
    }

    public final int score() {
        return score;
    }

    public final Score addBonusScore(final int bonusScore) {
        return new Score(addExact(this.score, bonusScore), bonusCount.decrease());
    }

    public final boolean isFinish() {
        return bonusCount.isFinish();
    }

}
