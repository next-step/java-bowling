package bowling.frame.domain;

import bowling.pin.domain.Pins;

import java.util.Objects;

public class NormalFrame extends Frame {

    private final int number;
    private final Pins pins;

    private NormalFrame(int number, Pins pins) {
        this.number = number;
        this.pins = pins;
    }

    public static Frame newFrame(int number, Pins pins) {
        return new NormalFrame(number, pins);
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return number == that.number &&
                Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, pins);
    }

    @Override
    public String toString() {
        return number + " : " + pins;
    }
}
