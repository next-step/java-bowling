package domain;

import domain.enums.StatusFrameEnum;
import domain.status.Spare;
import domain.status.Status;
import domain.status.Strike;

public class FrameStatus {

    private Pin pin;
    private StatusFrameEnum statusFrameEnum;

    public FrameStatus(int ball, StatusFrameEnum first) {
        this.pin = pin.firstPin(ball);
        this.statusFrameEnum = first;
    }

    public void statusUpdate(int ball, StatusFrameEnum second) {
        this.pin = this.pin.secondPin(ball);
        this.statusFrameEnum = second;
    }

    public boolean isStrike() {
        if (getFramePoint() instanceof Strike) {
            return true;
        }
        return false;
    }

    public boolean isSpare() {
        if (getFramePoint() instanceof Spare) {
            return true;
        }
        return false;
    }

    public Status getFramePoint() {
        return Status.of(pin, statusFrameEnum);
    }
}
