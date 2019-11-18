package score;

import java.util.Objects;

public class Score {
    private final Integer value;

    public Score(Integer value) {
        this.value = value;
    }

    public Integer getScore() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(value, score.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
