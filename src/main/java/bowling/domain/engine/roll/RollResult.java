package bowling.domain.engine.roll;

import bowling.dto.Exportable;

import java.util.Objects;

public abstract class RollResult implements Exportable<String> {

    private static final int MIN = 0;
    private static final int MAX = 10;

    private final int value;

    protected RollResult(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < MIN || MAX < value) {
            throw new IllegalArgumentException("쓰러트릴 수 있는 핀의 개수는 0 이상 10 이하 입니다.");
        }
    }

    public boolean isClear() {
        return this.value == MAX;
    }

    public boolean isClearWith(RollResult secondRoll) {
        return this.value + secondRoll.value == MAX;
    }

    public boolean exceedMaximumPins(RollResult secondRoll) {
        return this.value + secondRoll.value > MAX;
    }

    @Override
    public String export() {
        if (value == MAX) {
            return "X";
        }
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RollResult)) return false;
        RollResult that = (RollResult) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
