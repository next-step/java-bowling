package bowling.entity;

import java.util.Objects;

public class Strike implements Score {

    private final Pin score;

    public Strike(Pin score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strike strike = (Strike) o;
        return Objects.equals(score, strike.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
