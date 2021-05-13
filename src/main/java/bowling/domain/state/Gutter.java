package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

public class Gutter extends Running {
    private final Score score = Score.of();

    public static Gutter of() {
        return new Gutter();
    }

    @Override
    public Score eval() {
        return score;
    }

    @Override
    public State next(int nextHit) {
        if (Score.of(nextHit).isStrike()) {
            return Spare.of(this, OneHit.ofOne(nextHit));
        }
        return Miss.of(this, OneHit.of(nextHit));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gutter gutter = (Gutter) o;
        return Objects.equals(score, gutter.score);
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
