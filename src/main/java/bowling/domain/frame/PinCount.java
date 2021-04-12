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

    public PinCount(String count) {
        this(Integer.parseInt(count));
    }

    public int count() {
        return count;
    }

    public String countInString() {
        return Integer.toString(count());
    }

    public boolean isStrike() {
        return count == MAX_PIN_COUNT;
    }

    public boolean isGutter() {
        return count == MIN_PIN_COUNT;
    }

    public boolean isSpare(PinCount pinCount) {
        return (pinCount.count + count) == MAX_PIN_COUNT;
    }

    public boolean isValid(PinCount pinCount) {
        return (pinCount.count + count) <= MAX_PIN_COUNT;
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
