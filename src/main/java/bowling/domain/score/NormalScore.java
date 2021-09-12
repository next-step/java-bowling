package bowling.domain.score;

import java.util.Objects;

public class NormalScore extends Score {

    private final int score;

    private NormalScore(int score) {
        this.score = score;
    }

    public static NormalScore from(int score) {
        validateScore(score);
        return new NormalScore(score);
    }

    public NormalScore next(int score) {
        validateScore(score);
        validateCombinedScores(score);
        return new NormalScore(score);
    }

    private void validateCombinedScores(int score) {
        if (this.score + score > MAX_SCORE) {
            throw new IllegalArgumentException("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    public boolean isStrike() {
        return score == MAX_SCORE;
    }

    public int get() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScore that = (NormalScore) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
