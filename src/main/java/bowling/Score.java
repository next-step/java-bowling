package bowling;

import java.util.Objects;

public class Score {
    private static final int MAX_SCORE = 10;

    private final int value;

    public Score(int value) {
        validate(value);

        this.value = value;
    }

    public int sum(Score score) {
        return this.value + score.value;
    }

    public String getScoreString() {
        return String.valueOf(value);
    }

    public int toInt() {
        return value;
    }

    private void validate(int value) {
        if (value > MAX_SCORE) {
            throw new IllegalArgumentException("점수는 10점을 초과할수 없습니다.");
        }
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
