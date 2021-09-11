package bowling.domain;

import bowling.exception.InvalidFrameNumberException;

import java.util.Objects;

public class NormalFrame extends Frame {

    private NormalFrame(int number) {
        super(number);
    }

    public static NormalFrame of(int number) {
        validateFrameNumber(number);
        return new NormalFrame(number);
    }

    private static void validateFrameNumber(int number) {
        if (number < Frame.MIN_NORMAL_FRAME_NUMBER || number > Frame.MAX_NORMAL_FRAME_NUMBER) {
            throw new InvalidFrameNumberException(number);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return number == that.number && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, results);
    }

}
