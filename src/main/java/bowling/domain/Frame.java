package bowling.domain;

import java.util.Objects;

public class Frame {

    private Pin pin;
    private int countOfRemain;

    public Frame(Pin pin, int countOfRemain) {
        this.pin = pin;
        this.countOfRemain = countOfRemain;
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
