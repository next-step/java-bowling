package bowling.domain.score;

import bowling.exception.BonusCountNullPointerException;
import bowling.exception.InvalidScoreSizeException;

import java.util.Objects;

public final class Score {

    private static final int UN_AVAILABLE_SCORE = -1;
    private static final int MAXIMUM_SCORE = 30;

    private static final int ALL_PIN = 10;

    private final int score;
    private final BonusCount bonusCount;

    public static Score miss(final int missScore) {
        return new Score(missScore, BonusCount.none());
    }

    public static Score spare() {
        return new Score(ALL_PIN, BonusCount.spare());
    }


    public static final Score of(final int score, final BonusCount bonusCount) {
        return new Score(score, bonusCount);
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

}
