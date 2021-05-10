package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

public class Ready implements State {
    private final Score score = Score.of();

    public static Ready of() {
        return new Ready();
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
    public boolean isReady() {
        return true;
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
        return OneHit.of(nextHit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ready ready = (Ready) o;
        return Objects.equals(score, ready.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "";
    }
}
