package bowling;

import java.util.Objects;

public class Score {
    private final static int MAX_SCORE = 10;

    private final int value;

    public Score(int value) {
        validate(value);

        this.value = value;
    }

    private void validate(int value) {
        if (value > MAX_SCORE) {
            throw new IllegalArgumentException("점수는 10점을 초과할수 없습니다.");
        }
    }

    public Score sum(Score score) {
        return new Score(this.value + score.value);
    }

    public Boolean isMaxScore() {
        return value == MAX_SCORE;
    }

    public FrameResult getSpareOrMiss() {
        if (value == MAX_SCORE) {
            return FrameResult.SPARE;
        }

        return FrameResult.MISS;
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
