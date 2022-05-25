package bowling.domain.score;

import bowling.domain.Pins;
import bowling.exception.BonusCountNullPointerException;
import bowling.exception.InvalidMissPinsException;
import bowling.exception.InvalidScoreException;

import java.util.Objects;

public class Score {

    private static final int UN_AVAILABLE_SCORE = -1;

    private static final int ALL_PIN_COUNT = 10;

    private static final int MAX_SCORE = 30;

    private final int score;

    private final BonusCount bonusCount;

    private Score(int score, BonusCount bonusCount) {
        validateParam(score, bonusCount);
        this.score = score;
        this.bonusCount = bonusCount;
    }

    private void validateParam(int score, BonusCount bonusCount) {
        validateScore(score);
        validateNullable(bonusCount);
    }

    private void validateScore(int score) {
        if (score < UN_AVAILABLE_SCORE || score > MAX_SCORE) {
            throw new InvalidScoreException(score);
        }
    }

    private void validateNullable(BonusCount bonusCount) {
        if (Objects.isNull(bonusCount)) {
            throw new BonusCountNullPointerException();
        }
    }

    public static Score miss(Pins pins) {
        if(pins.isMiss()) {
            return new Score(pins.count(), BonusCount.none());
        }
        throw new InvalidMissPinsException(pins);
    }

    public static Score spare() {
        return new Score(ALL_PIN_COUNT, BonusCount.spare());
    }

    public static Score strike() {
        return new Score(ALL_PIN_COUNT, BonusCount.strike());
    }

    public static Score unavailable() {
        return new Score(UN_AVAILABLE_SCORE, BonusCount.none());
    }

    public Score addBonusScore(int bonusScore) {
        return new Score(this.score + bonusScore, bonusCount.decrease());
    }

    public boolean finishCalculation() {
        return bonusCount.isEmpty();
    }

    public boolean isUnavailable() {
        return score == UN_AVAILABLE_SCORE;
    }

    public int score() {
        return score;
    }

    @Override
    public String toString() {
        return isUnavailable() ? "" : String.valueOf(score);
    }

}
