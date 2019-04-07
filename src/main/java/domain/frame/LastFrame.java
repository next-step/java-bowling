package domain.frame;

import domain.pin.Pin;
import domain.status.Status;

import static domain.frame.Frames.LAST_FRAME;

public class LastFrame extends Frame {

    public LastFrame(int number, Pin pin) {
        super(number, pin);

        if(number != LAST_FRAME) {
            throw new IllegalArgumentException("잘못된 프레임 번호입니다.");
        }
    }

    @Override
    public boolean isFinished() {
        if(statuses.isEmpty()) {
            return false;
        }

        if(pins.size() > 2) {
            return true;
        }

        Status lastStatus = getLastStatus();
        return lastStatus.isNormalFrameFinished() && !lastStatus.isClear();
    }
}