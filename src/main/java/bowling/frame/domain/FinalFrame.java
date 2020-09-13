package bowling.frame.domain;

import bowling.pin.domain.Pins;

import java.util.Objects;

public class FinalFrame extends Frame {

    private int number;
    private Pins pins;

    private FinalFrame(int number, Pins pins) {
        this.number = number;
        this.pins = pins;
    }

    public static Frame finalFrame(Pins pins) {
        return new FinalFrame(Frames.FINAL_FRAME_COUNT, pins);
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
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
