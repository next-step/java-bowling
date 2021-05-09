package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

public class Gutter extends State {
    private final Score score = Score.of();

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
    public boolean canAccumulate() {
        return false;
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
