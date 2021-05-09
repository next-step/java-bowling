package bowling.domain.score;

import bowling.exception.BonusCountNullPointerException;

import java.util.Objects;

public final class Score {

    private final int score;
    private final BonusCount bonusCount;

    private Score(final int score, final BonusCount bonusCount) {
        validateNull(bonusCount);
        this.score = score;
        this.bonusCount = bonusCount;
    }

    private final void validateNull(final BonusCount bonusCount) {
        if (Objects.isNull(bonusCount)) {
            throw new BonusCountNullPointerException();
        }
    }

    public static Score of(final int score, final BonusCount bonusCount) {
        return new Score(score, bonusCount);
    }
}
