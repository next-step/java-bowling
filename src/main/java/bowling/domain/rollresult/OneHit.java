package bowling.domain.rollresult;

import bowling.domain.Score;

import java.util.Objects;

public class OneHit extends RollResultType{
    private final Score score;

    public OneHit(Score score) {
        this.score = score;
    }

    public static OneHit ofOne(int score) {
        return ofOne(Score.of(score));
    }

    public static OneHit ofOne(Score score) {
        return new OneHit(score);
    }

    public static RollResultType of(int score) {
        return of(Score.of(score));
    }

    public static RollResultType of(Score score) {
        if (score.isEqual(DEFAULT_MAX_SCORE)) {
            return Strike.of();
        }
        if (score.isEqual(DEFAULT_MIN_SCORE)) {
            return Gutter.of();
        }
        return new OneHit(score);
    }

    public OneHit add(int nextScore) {
        return OneHit.ofOne(score.add(nextScore));
    }

    @Override
    public RollResultType next(int nextHit) {
        if (score.isEqual(DEFAULT_MAX_SCORE - nextHit)) {
            return Spare.of(this, OneHit.ofOne(Score.of(nextHit)));
        }
        if (score.isEqual(0) && nextHit == 0) {
            return Gutter.of();
        }
        return Miss.of(this, OneHit.of(nextHit));
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
        return score.eval();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneHit oneHit = (OneHit) o;
        return Objects.equals(score, oneHit.score);
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
