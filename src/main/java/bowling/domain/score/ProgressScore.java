package bowling.domain.score;

import static bowling.domain.pin.Pins.MAX_COUNT_HIT_PINS;

public class ProgressScore implements Score {
    public static final int FINAL_LIMIT_COUNT = 0;
    private static final int SPARE_LEFT_COUNT = 1;
    private static final int COMMON_LEFT_COUNT = 2;

    private final int value;
    private final int count;

    private ProgressScore(int score, int count) {
        validate(count);

        this.value = score;
        this.count = count;
    }

    private void validate(int leftCount) {
        if (leftCount > COMMON_LEFT_COUNT) {
            throw new IllegalArgumentException("left count는 " + COMMON_LEFT_COUNT + " 를 넘기면 안된다");
        }
    }

    public static ProgressScore ofStrike() {
        return of(MAX_COUNT_HIT_PINS, COMMON_LEFT_COUNT);
    }

    public static ProgressScore ofSpare() {
        return of(MAX_COUNT_HIT_PINS, SPARE_LEFT_COUNT);
    }

    public static ProgressScore of(int score, int leftCount) {
        return new ProgressScore(score, leftCount);
    }

    @Override
    public boolean isCompute() {
        return false;
    }

    @Override
    public Score add(Score score) {
        int nextLeftCount = count - 1;

        if (nextLeftCount == FINAL_LIMIT_COUNT) {
            return ComputableScore.of(value + score.getValue());
        }

        return ProgressScore.of(value + score.getValue(), nextLeftCount);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgressScore that = (ProgressScore) o;

        if (value != that.value) return false;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + count;
        return result;
    }
}
