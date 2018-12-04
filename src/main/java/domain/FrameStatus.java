package domain;

import domain.enums.StatusFrameEnum;
import domain.enums.StatusPointEnum;

public class FrameStatus {

    private Pin pin;
    private StatusFrameEnum statusFrameEnum;
    private BowlingResult bowlingResult;

    public FrameStatus(int ball, StatusFrameEnum first) {
        this.pin = pin.firstPin(ball);
        this.statusFrameEnum = first;
        this.bowlingResult = new BowlingResult();
        writeCurrentResult();
    }

    public void statusUpdate(int ball, StatusFrameEnum second) {
        this.pin = this.pin.secondPin(ball);
        this.statusFrameEnum = second;
        writeCurrentResult();
    }

    public int getLeftPinCount() {
        return this.pin.getPin();
    }

    private void writeCurrentResult() {
        if (getFramePoint() == StatusPointEnum.STRIKE) {
            bowlingResult.appendFirst(" X");
            bowlingResult.appendSecond("  ");
        }

        if (getFramePoint() == StatusPointEnum.SPARE) {
            bowlingResult.appendSecond("/");
        }

        if (getFramePoint() == StatusPointEnum.FIRSTGUTTER) {
            bowlingResult.appendFirst("-|");
        }

        if (getFramePoint() == StatusPointEnum.SECONDGUTTER) {
            bowlingResult.appendSecond("-");
        }

        if (getFramePoint() == StatusPointEnum.MISS && statusFrameEnum == StatusFrameEnum.FIRST) {
            bowlingResult.appendFirst(" " + String.valueOf(getLeftPinCount()) + "|");
        }

        if (getFramePoint() == StatusPointEnum.MISS && statusFrameEnum == StatusFrameEnum.SECOND) {
            bowlingResult.appendSecond(String.valueOf(getLeftPinCount()));
        }
    }

    public boolean isStrike() {
        if (getFramePoint() == StatusPointEnum.STRIKE) {
            return true;
        }
        return false;
    }

    public boolean isSpare() {
        if (getFramePoint() == StatusPointEnum.SPARE) {
            return true;
        }
        return false;
    }

    public StatusPointEnum getFramePoint() {
        return StatusPointEnum.Of(pin, statusFrameEnum);
    }

    public String getCurrentResultFirst() {
        return bowlingResult.getFirstResult();
    }

    public String getCurrentResultSecond() {
        return bowlingResult.getSecondResult();
    }
}
