package bowling.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pin {

    private static final String NO_RANGE_EXCEPTION_MESSAGE_FORMAT = "핀의 갯수는 %d ~ %d 사이여야 합니다.";
    private static final Pin[] zeroThroughTen = {
            new Pin(0),
            new Pin(1),
            new Pin(2),
            new Pin(3),
            new Pin(4),
            new Pin(5),
            new Pin(6),
            new Pin(7),
            new Pin(8),
            new Pin(9),
            new Pin(10)
    };

    public static final int MAX = 10;
    public static final int MIN = 0;
    public static final Pin TEN = zeroThroughTen[MAX];

    private final int count;

    private Pin(int count) {
        this.count = count;
    }

    private static void validCount(int count) {
        if(count < MIN || count > MAX) {
            throw new IllegalArgumentException(String.format(NO_RANGE_EXCEPTION_MESSAGE_FORMAT, MIN, MAX));
        }
    }

    public static Pin from(int count) {
        validCount(count);
        return zeroThroughTen[count];
    }

    public Pin add(Pin other) {
        return Pin.from(this.count + other.count);
    }

    public boolean isMaxCount() {
        return this.count == MAX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pin pin = (Pin) o;
        return count == pin.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
