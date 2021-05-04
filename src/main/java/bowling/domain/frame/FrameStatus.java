package bowling.domain.frame;

import bowling.domain.pin.BallThrows;
import bowling.domain.pin.Pin;

public enum FrameStatus {
    STRIKE,
    SPARE,
    MISS,
    NORMAL,
    NOT_ENDED;

    public static FrameStatus of(BallThrows ballThrows) {
        if (ballThrows.isFirstThrow()) {
            return NOT_ENDED;
        }
        if (ballThrows.firstPin().isMaximum()) {
            return STRIKE;
        }
        if (ballThrows.isSecondThrow()) {
            return NOT_ENDED;
        }
        final Pin sum = ballThrows.firstPin().sum(ballThrows.secondPin());
        if (sum.isMaximum()) {
            return SPARE;
        }
        if (sum.pinCount() == 0) {
            return MISS;
        }
        return NORMAL;
    }
}
