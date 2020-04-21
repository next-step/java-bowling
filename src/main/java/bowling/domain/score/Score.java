package bowling.domain.score;

import java.util.Objects;

public class Score {
    public static final int ZERO = 0;
    public static final Score NOT_ADDABLE_SCORE = new Score(-1, -1);
    public static final Score INIT_SCORE = new Score(ZERO, ZERO);

    private final int score;
    private final int leftBonusCount;

    public Score(final int score, final int leftBonusCount) {
        this.score = score;
        this.leftBonusCount = leftBonusCount;
    }

    public Score add(final Score other) {
        return new Score(score + other.score, leftBonusCount + other.leftBonusCount);
    }

    public Score accumulate(final int knockOverCount) {
        return new Score(score + knockOverCount, leftBonusCount - 1);
    }

    public boolean isCompleteAccumulation() {
        return leftBonusCount == ZERO;
    }

    public boolean isNotAddable() {
        return this.equals(NOT_ADDABLE_SCORE);
    }

    public int getValue() {
        return score;
    }

    public int getLeftBonusCount() {
        return leftBonusCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score1 = (Score) o;
        return score == score1.score &&
                leftBonusCount == score1.leftBonusCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, leftBonusCount);
    }
}
