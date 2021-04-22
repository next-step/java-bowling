package bowling.domain;

import java.util.Objects;

public class PitchResult {

    private static final int MINIMUM_VALUE = 0;
    private static final int MAXIMUM_VALUE = 10;

    private final int value;

    public PitchResult(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < MINIMUM_VALUE || MAXIMUM_VALUE < value) {
            throw new IllegalArgumentException("쓰러트릴 수 있는 핀의 개수는 0 이상 10 이하 입니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PitchResult)) return false;
        PitchResult that = (PitchResult) o;
        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
