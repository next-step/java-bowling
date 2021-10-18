package bowling.domain;

public class SumScore {
    private static final int MIN = 0;

    private static final int MAX = 300;

    public static final SumScore ZERO = new SumScore(0);

    private final int sumScore;

    public SumScore(final int sumScore) {
        validateRange(sumScore);
        this.sumScore = sumScore;
    }

    private void validateRange(final int sumScore) {
        if (sumScore < MIN || sumScore > MAX) {
            throw new IllegalArgumentException("sumScore 범위를 벗어났습니다. sumScore : " + sumScore);
        }
    }

    public SumScore plus(final Score score) {
        return new SumScore(sumScore + score.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(sumScore);
    }
}
