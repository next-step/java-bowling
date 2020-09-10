package bowling.domain;

import java.util.Objects;

public class Pins {
    public static final int COUNT = 10;
    private static final int CLEAR_COUNT = 0;

    private int count;

    private Pins(int count) {
        this.count = count;
    }

    public static Pins from(int count) {
        if (count != COUNT) {
            throw new IllegalArgumentException(String.format("핀의 갯수는 %s보다 많거나 작을 수 없습니다. [%s]", COUNT, count));
        }

        return new Pins(count);
    }

    public static Pins create() {
        return Pins.from(COUNT);
    }

    public int hitting(int hitCount) {
        if (count < hitCount) {
            throw new IllegalArgumentException(String.format("남아있는 핀의 갯수 [%s] 보다 큰 값이 입력되었습니다. [%s]", count, hitCount));
        }

        count -= hitCount;

        return count;
    }

    public boolean isClear() {
        return count == CLEAR_COUNT;
    }

    public int getDownPins() {
        return COUNT - count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return count == pins.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "Pins{" +
                "count=" + count +
                '}';
    }
}
