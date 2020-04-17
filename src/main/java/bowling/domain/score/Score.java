package bowling.domain.score;

import java.util.Objects;

public class Score {
    public static final int ZERO = 0;
    public static final Score NOT_ADDABLE_SCORE = new Score(-1, -1);
    public static final Score INIT_SCORE = new Score(ZERO, ZERO);

    private final int score;
    private final int left;

    public Score(final int score, final int left) {
        this.score = score;
        this.left = left;
    }

    public Score add(final Score other) {
        return new Score(score + other.score, left + other.left);
    }

    public Score accumulate(final int knockOverCount) {
        return new Score(score + knockOverCount, left - 1);
    }

    public int getValue() {
        return score;
    }

    public int getLeft() {
        return left;
    }

    public boolean isCompleteAccumulation() {
        return left == ZERO;
    }

    public boolean isNotAddable() {
        return this.equals(NOT_ADDABLE_SCORE);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score1 = (Score) o;
        return score == score1.score &&
                left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
