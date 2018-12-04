package domain;

import domain.enums.StatusFrameEnum;
import domain.enums.StatusPointEnum;

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

    public StatusPointEnum getFramePoint() {
        return StatusPointEnum.Of(this.pin.getPin(), statusFrameEnum);
    }

    public int getLeftPinCount() {
        return this.pin.getPin();
    }
}
