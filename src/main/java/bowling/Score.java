package bowling;

import java.util.Objects;

public class Score {
    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score sum(Score score) {
        return new Score(this.value + score.value);
    }

    public boolean isHigherOrEqualThan(Score score) {
        return this.value >= score.value;
    }

    public String getScoreString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
