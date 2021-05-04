package bowling.domain.rollresult;

import java.util.Objects;

public class Gutter extends RollResultType {
    private final int score = DEFAULT_MIN_SCORE;

    private Gutter() {
    }

    public static Gutter of() {
        return new Gutter();
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
        return DEFAULT_MIN_SCORE;
    }

    @Override
    public RollResultType next(int nextHit) {
        if (nextHit == DEFAULT_MAX_SCORE) {
            return Spare.of(this, OneHit.ofOne(nextHit));
        }
        return Miss.of(this, OneHit.of(nextHit));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gutter gutter = (Gutter) o;
        return score == gutter.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "-";
    }
}
