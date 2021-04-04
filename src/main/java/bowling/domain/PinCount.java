package bowling.domain;

public class PinCount {

    private static final int MIN_PIN_COUNT = 0;

    private static final int MAX_PIN_COUNT = 10;

    private final int pinCount;

    public PinCount(int pinCount) {
        if (pinCount < MIN_PIN_COUNT || pinCount > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("투구수는 0-10사이어야 합니다.");
        }
        this.pinCount = pinCount;
    }

    public int count() {
        return pinCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PinCount pinCount1 = (PinCount) o;

        return pinCount == pinCount1.pinCount;
    }

    @Override
    public int hashCode() {
        return pinCount;
    }
}
