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
    protected void addNextStatus(Pin pin) {
        statuses.add(getLastStatus().getNext(pin));
    }

    private boolean isBonusBowl() {
        return pins.size() > 2;
    }

    @Override
    public Frame bowl(Pin pin) {
        addPin(pin);
        addNextStatus(pin);
        return this;
    }

    @Override
    public boolean isFinished() {
        if(statuses.isEmpty()) {
            return false;
        }

        if(isBonusBowl()) {
            return true;
        }

        Status lastStatus = getLastStatus();
        return lastStatus.isNormalFrameFinished() && !lastStatus.isClear();
    }
}