package bowling.domain;

import java.util.Objects;

public class Pin {

    public static final int MAX = 10;
    public static final int MIN = 0;
    private final int count;

    private Pin(int count) {
        if(count < MIN || count > MAX) {
            throw new IllegalArgumentException();
        }
        this.count = count;
    }

    public static Pin from(int count) {
        return new Pin(count);
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
