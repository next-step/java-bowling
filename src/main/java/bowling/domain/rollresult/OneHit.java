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
        if (score.isStrike()) {
            return Strike.of();
        }
        if (score.isGutter()) {
            return Gutter.of();
        }
        return new OneHit(score);
    }

    public OneHit add(RollResultType result, int nextScore) {
        return OneHit.ofOne(score.add(result, nextScore));
    }

    @Override
    public RollResultType next(int nextHit) {
        if (score.add(nextHit).isStrike()) {
            return Spare.of(this, OneHit.ofOne(Score.of(nextHit)));
        }
        if (score.add(nextHit).isGutter()) {
            return Miss.of(Gutter.of(), Gutter.of());
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
    public boolean isCalculated() {
        return false;
    }

    public boolean isCalculated(RollResultType result) {
        return score.isFinished(result);
    }

    @Override
    public Score eval() {
        return score;
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
