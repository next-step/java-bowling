package bowling.rollresult;

import java.util.Objects;

public class OneRollResultType implements RollResultType{
    private final int score;

    private OneRollResultType(int score) {
        this.score = score;
    }

    public static RollResultType of(int score) {
        if (score == DEFAULT_MAX_SCORE) {
            return Strike.of();
        }
        return new OneRollResultType(score);
    }

    @Override
    public RollResultType next(int nextHit) {
        if (nextHit == DEFAULT_MAX_SCORE) {
            return Spare.of(score);
        }
        if (score == 0 && nextHit == 0) {
            return Miss.of();
        }
        return Gutter.of(score, nextHit - score);
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
        OneRollResultType that = (OneRollResultType) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
