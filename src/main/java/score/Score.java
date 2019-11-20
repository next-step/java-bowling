package score;

import java.util.Objects;

public class Score {
    private final Integer score;

    public Score(Integer score) {
        validateScore(score);
        this.score = score;
    }

    private void validateScore(Integer score) {
        if (score < 0 || 10 < score) {
            throw new IllegalArgumentException(score + "는 올바른 점수가 아닙니다.");
        }
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return Objects.equals(score, score1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
