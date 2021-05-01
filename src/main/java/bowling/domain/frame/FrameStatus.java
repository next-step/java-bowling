package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

public enum FrameStatus {
    STRIKE,
    SPARE,
    MISS,
    NORMAL,
    NOT_ENDED;

    public static FrameStatus of(Pins pins) {
        if (pins.isEmpty()) {
            return NOT_ENDED;
        }
        if (pins.firstPin().isMaximum()) {
            return STRIKE;
        }
        if (pins.size() == 1) {
            return NOT_ENDED;
        }
        final Pin sum = pins.firstPin().sum(pins.secondPin());
        if (sum.isMaximum()) {
            return SPARE;
        }
        if (sum.pinCount() == 0) {
            return MISS;
        }
        return NORMAL;
    }
}
