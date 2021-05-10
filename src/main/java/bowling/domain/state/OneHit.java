package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

public class OneHit extends Running {
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

    public static State of(int score) {
        return of(Score.of(score));
    }

    public static State of(Score score) {
        if (score.isStrike()) {
            return Strike.of();
        }
        if (score.isGutter()) {
            return Gutter.of();
        }
        return new OneHit(score);
    }

    @Override
    public State next(int nextHit) {
        if (score.calculate(nextHit).isClear()) {
            return Spare.of(this, ofOne(Score.ofSpare(nextHit)));
        }
        return Miss.of(this, of(nextHit));
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
