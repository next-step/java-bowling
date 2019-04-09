package domain.frame;

import domain.pin.Pin;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;

public class NormalFrame extends Frame {

    public NormalFrame(int number, Pin pin) {
        super(number, pin);

        if (number < START_FRAME || number >= LAST_FRAME) {
            throw new IllegalArgumentException("잘못된 프레임 번호입니다.");
        }
    }

    @Override
    public Frame bowl(Pin pin) {
        if (isFinished()) {
            return createNextFrame(pin);
        }

        return super.bowl(pin);
    }

    @Override
    public boolean isFinished() {
        return !statuses.isEmpty() && getLastStatus().isNormalFrameFinished();
    }
}