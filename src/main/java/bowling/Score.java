package bowling;

import java.util.Objects;

public class Score {

    private final int value;

    public Score(int value) {
        validate(value);

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

    public static Score ofZero() {
        return new Score(0);
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

    private void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("점수는 음수가 될 수 없습니다.");
        }
    }
}
