package bowling.domain.frame;

import bowling.domain.Pins;

import static bowling.domain.BowlingErrorMessage.INVALID_FRAME_NUMBER;
import static bowling.domain.BowlingProperty.NORMAL_FRAME_END_NUMBER;
import static bowling.domain.BowlingProperty.NORMAL_FRAME_START_NUMBER;
import static com.google.common.base.Preconditions.checkArgument;

public class NormalFrame extends Frame {

    public NormalFrame(int number) {
        super(number);
        checkArgument(number >= NORMAL_FRAME_START_NUMBER && number <= NORMAL_FRAME_END_NUMBER,
                INVALID_FRAME_NUMBER);
    }

    @Override
    public boolean cannotRollMore(Pins pins) {
        return pins.isNotAvailable();
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }
}
