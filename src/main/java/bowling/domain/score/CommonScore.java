package bowling.domain.score;

import static bowling.domain.pin.Pins.MAX_COUNT_HIT_PINS;

public class CommonScore implements Score {
    private static final int BASE_VALUE = 0;
    private static final int MAX_VALUE = 30;

    protected final int value;

    protected CommonScore(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int score) {
        if (score > MAX_VALUE) {
            throw new IllegalArgumentException("Score는 " + MAX_VALUE + " 값을 넘기면 안됩니다");
        }
    }

    public static CommonScore ofStrike() {
        return of(MAX_COUNT_HIT_PINS);
    }

    public static CommonScore ofBase() {
        return of(BASE_VALUE);
    }

    public static CommonScore of(int score) {
        return new CommonScore(score);
    }

    @Override
    public boolean isCompute() {
        return false;
    }

    @Override
    public Score add(Score score) {
        return new CommonScore(this.value + score.getValue());
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonScore score1 = (CommonScore) o;

        return value == score1.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
