package bowling.domain;

import java.util.Objects;

public class Frame {

    private Pin pin;
    private int countOfRemain;

    public Frame(int countOfHit, int countOfRemain) {
        this.pin = new Pin(countOfHit);
        this.countOfRemain = countOfRemain;
    }

    public Pin hit() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return countOfRemain == frame.countOfRemain &&
                Objects.equals(pin, frame.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, countOfRemain);
    }
}
