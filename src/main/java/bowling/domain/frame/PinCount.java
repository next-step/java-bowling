package bowling.domain.frame;

public class PinCount {

    private static final int MIN_PIN_COUNT = 0;

    private static final int MAX_PIN_COUNT = 10;

    private final int count;

    public PinCount(int count) {
        if (count < MIN_PIN_COUNT || count > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("투구수는 0-10사이어야 합니다.");
        }
        this.count = count;
    }

    public int count() {
        return count;
    }

    public int sumCount(PinCount pinCount) {
        return pinCount.count + count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PinCount pinCount1 = (PinCount) o;

        return count == pinCount1.count;
    }

    @Override
    public int hashCode() {
        return count;
    }
}
