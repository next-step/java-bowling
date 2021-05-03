package bowling.domain.rollresult;

import java.util.Objects;

public class OneHit implements RollResultType{
    private final int score;

    private OneHit(int score) {
        this.score = score;
    }

    public static RollResultType of(int score) {
        if (score == DEFAULT_MAX_SCORE) {
            return Strike.of();
        }
        if (score == DEFAULT_MIN_SCORE) {
            return Gutter.of();
        }
        return new OneHit(score);
    }

    public static OneHit ofOne(int score) {
        return new OneHit(score);
    }

    public OneHit add(int nextScore) {
        return new OneHit(score + nextScore);
    }

    @Override
    public RollResultType next(int nextHit) {
        if (nextHit == DEFAULT_MAX_SCORE) {
            return Spare.of(this, new OneHit(nextHit - score));
        }
        if (score == 0 && nextHit == 0) {
            return Gutter.of();
        }
        return Miss.of(this, OneHit.of(nextHit - score));
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public int eval() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneHit that = (OneHit) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "" + score + "";
    }
}
