package bowling.domain.score;

import java.util.Objects;

public class FinalScore extends Score {
    private final int third;

    private FinalScore(int first, int second, int third) {
        super(first, second);
        this.third = third;
    }

    public static FinalScore start() {
        return new FinalScore(0, 0, 0);
    }

    public static FinalScore first(int score) {
        validateScore(score);
        return new FinalScore(score, 0, 0);
    }

    public FinalScore second(int score) {
        validateScore(score);
        validateCombinedScores(score);
        return new FinalScore(getFirst(), score, this.third);
    }

    public FinalScore third(int score) {
        validateScore(score);
        return new FinalScore(getFirst(), getSecond(), score);
    }

    public int getThird() {
        return third;
    }

    @Override
    protected void validateCombinedScores(int score) {
        if (!isStrike() && getFirst() + score > MAX_SCORE) {
            throw new IllegalArgumentException(
                    "마지막 프레임의 첫시도가 스트라이크가 아닐 때, 1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalScore that = (FinalScore) o;
        return third == that.third;
    }

    @Override
    public int hashCode() {
        return Objects.hash(third);
    }
}
