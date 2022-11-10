package bowling;

import java.util.Objects;

public class BowlingPin {
    public static final int MAX_PIN_NUMBER = 10;
    public static final int ZERO = 0;

    private final int count;

    public BowlingPin(int count) {
        if (count < ZERO || count > MAX_PIN_NUMBER) {
            throw new IllegalArgumentException("볼링핀의 갯수는 0~10사이여야만 합니다.");
        }
        this.count = count;
    }

    public BowlingPin hitPins(BowlingPin bowlingPin) {
        return new BowlingPin(this.count - bowlingPin.count);
    }


    public boolean isZero() {
        return count == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingPin that = (BowlingPin) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
