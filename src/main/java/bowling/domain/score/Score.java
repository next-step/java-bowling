package bowling.domain.score;

import java.util.Objects;

public class Score {
    protected final int score;

    public Score(final int score) {
        this.score = score;
    }

    public final Score sum(final Score score) {
        return new Score(this.score + score.score);
    }

    public boolean isEmpty() {
        return InnerLazyClass.EMPTY_SCORE.equals(this);
    }

    public boolean isZero() {
        return this.score == 0;
    }

    public final int value() {
        return score;
    }

    @Override
    public boolean equals(final Object compareValue) {
        if (this == compareValue) {
            return true;
        }
        if (!(compareValue instanceof Score)) {
            return false;
        }
        final Score compareScore = (Score) compareValue;
        return score == compareScore.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "Score{" + "score=" + score + '}';
    }

    private static class InnerLazyClass {
        private static final int EMPTY_VALUE = -1;

        private static final Score EMPTY_SCORE = new Score(EMPTY_VALUE);
    }
}
