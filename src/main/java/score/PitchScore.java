package score;

import java.util.Objects;

public class PitchScore {
    private final Integer score;

    public PitchScore(Integer score) {
        validateScore(score);
        this.score = score;
    }

    private void validateScore(Integer score) {
        if (score < 0 || 10 < score) {
            throw new IllegalArgumentException(score + "는 올바른 점수가 아닙니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchScore that = (PitchScore) o;
        return Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
