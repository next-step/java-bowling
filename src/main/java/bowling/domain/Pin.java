package bowling.domain;

import java.util.Objects;

public class Pin {
    private static final int MAX_PIN = 10;
    private static final int MIN_PIN = 0;

    private final int number;

    private Pin(int number) {
        this.number = number;
    }

    public static Pin of(int number) {
        return new Pin(number);
    }

    public Pin leftPin(){
        return new Pin(MAX_PIN - number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return number == pin.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
