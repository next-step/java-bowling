package bowling.frame.domain;

import bowling.global.exception.OutOfFrameRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.pin.domain.Pins;

import java.util.Objects;

public class NormalFrame extends Frame {

    private final int number;
    private final int pitchCount = 2;
    private final Pins pins;

    public NormalFrame(int number, Pins pins) {
        this.number = number;
        this.pins = pins;
        validateFrameRange();
    }

    public static Frame newFrame(int number, Pins pins) {
        return new NormalFrame(number, pins);
    }

    public void validateFrameRange() {
        if (number < 1 || number > 9) {
            throw new OutOfFrameRangeException(ExceptionMessage.INVALID_NOMAL_FRAME_NUMBER);
        }
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
        System.out.println();
        return number + "Frame / " + pins;
    }
}
