package bowling.domain;

public class PinCount {

    private static final int MAX = 10;
    private static final int MIN = 0;
    private final int value;

    private PinCount(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException("0 ~ 10 사이 숫자만 가능합니다. " + value);
        }
    }

    public static PinCount of(int value) {
        return new PinCount(value);
    }

    public int getValue() {
        return value;
    }

    public boolean isZero() {
        return value == 0;
    }

    //===================================================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PinCount pinCount = (PinCount) o;

        return value == pinCount.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }

    public int sum(PinCount pinCount) {
        return value + pinCount.getValue();
    }

    public boolean isTen() {
        return value == 10;
    }
}
