package bowling.domain.frame;

import bowling.exception.ValueOutOfRangeException;
import bowling.exception.message.ErrorMessage;

import java.util.Objects;

public class FrameNumber {

    static final int FRAME_STEP = 1;
    static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 10;

    private final int no;

    private FrameNumber(final int no) {
        validRange(no);
        this.no = no;
    }

    public static FrameNumber of(final int no) {
        return new FrameNumber(no);
    }

    private void validRange(final int no) {
        if (no < MIN_NUMBER || no > MAX_NUMBER) {
            throw new ValueOutOfRangeException(ErrorMessage.REQUIRED_FRAME_RANGE);
        }
    }

    public FrameNumber increase() {
        return new FrameNumber(this.no + FRAME_STEP);
    }

    public boolean isFinal() {
        return this.no == MAX_NUMBER;
    }

    public int getNo() {
        return this.no;
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
