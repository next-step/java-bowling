package bowling.domain.score;

import java.util.Objects;

public class Score {
    protected int score;

    protected Score(int score) {
        this.score = score;
    }

    public final Score sum(Score score) {
        return new Score(this.score + score.score);
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
        return String.valueOf(score);
    }
}
