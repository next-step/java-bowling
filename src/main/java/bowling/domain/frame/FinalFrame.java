package bowling.domain.frame;

import bowling.domain.Pins;

import static bowling.domain.BowlingErrorMessage.INVALID_FRAME_NUMBER;
import static bowling.domain.BowlingProperty.FINAL_FRAME_NUMBER;
import static com.google.common.base.Preconditions.checkArgument;

public class FinalFrame extends Frame {

    private boolean bonus = false;

    public FinalFrame(int number) {
        super(number);
        checkArgument(number == FINAL_FRAME_NUMBER, INVALID_FRAME_NUMBER);
    }

    @Override
    public boolean cannotRollMore(Pins pins) {
        if (!bonus && pins.isAllPinsDown()) {
            bonus = true;
        }
        if (pins.isNotAvailable()) {
            pins.reset();
        }
        return bonus
                ? getRollRecord().isRollThreeTimes()
                : getRollRecord().isRollTwice();
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }
}
