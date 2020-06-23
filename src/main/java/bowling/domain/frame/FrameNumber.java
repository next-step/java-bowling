package bowling.domain.frame;

import bowling.exception.ValueOutOfRangeException;
import bowling.exception.message.ErrorMessage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameNumber {

    static final int FRAME_STEP = 1;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 10;
    private static final List<FrameNumber> NUMBERS =
            IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                    .boxed()
                    .map(FrameNumber::new)
                    .collect(Collectors.toList());

    private final int no;

    private FrameNumber(final int no) {
        validateRange(no);
        this.no = no;
    }

    public static FrameNumber of(final int no) {
        return getFrameNumber(no);
    }

    private void validateRange(final int no) {
        if (no < MIN_NUMBER || no > MAX_NUMBER) {
            throw new ValueOutOfRangeException(ErrorMessage.REQUIRED_FRAME_RANGE);
        }
    }

    public FrameNumber increase() {
        return getFrameNumber(this.no + FRAME_STEP);
    }

    public boolean isFinal() {
        return this.no == MAX_NUMBER;
    }

    public int getNo() {
        return this.no;
    }

    private static FrameNumber getFrameNumber(final int no) {
        return NUMBERS.get(no - MIN_NUMBER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FrameNumber)) return false;
        FrameNumber that = (FrameNumber) o;
        return no == that.no;
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }
}
