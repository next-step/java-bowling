package bowling.domain.score;

import java.util.Objects;

public class FinalScore extends Score {

    private final int score;

    private FinalScore(int score) {
        this.score = score;
    }

    public static FinalScore from(int score) {
        validateScore(score);
        return new FinalScore(score);
    }

    public FinalScore next(int score) {
        validateScore(score);
        return new FinalScore(score);
    }

    public boolean isSpareWhenAdd(int score) {
        return this.score != MAX_SCORE && this.score + score >= MAX_SCORE;
    }

    public boolean isStrike() {
        return score == MAX_SCORE;
    }

    public int get() {
        return score;
    }

    protected static void validateScore(int score) {
        if (outOfRange(score)) {
            throw new IllegalArgumentException("잘못된 점수를 입력하였습니다.");
        }
    }

    private static boolean outOfRange(int score) {
        return score < 0 || score > MAX_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalScore that = (FinalScore) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
