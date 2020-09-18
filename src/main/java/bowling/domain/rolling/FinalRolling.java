package bowling.domain.rolling;

import bowling.domain.Pin;

public class FinalRolling extends Rolling {

    @Override
    public void bowl(Pin pin) {
        rollingPin.add(pin);

        if (rollingPin.size() > 1) {
            record(beforeFrameRecord(pin));
            return;
        }
        record(pin.record());
    }

    @Override
    public boolean isFinish() {
        if (canFinalFrameBowl() || isFinishFinalBowl()) {
            return true;
        }
        return false;
    }

    private String beforeFrameRecord(Pin pin) {
        if (beforePin().isStrike() || isSpareSecondFrame()) {
            return pin.record();
        }
        return beforePin().record(pin);
    }

    private boolean isSpareSecondFrame() {
        if (rollingPin.size() < 3) {
            return false;
        }
        return firstPin().isSpare(beforePin());
    }

    private boolean isFinishFinalBowl() {
        return rollingPin.size() > 2;
    }

    private boolean canFinalFrameBowl() {
        return countOfFirstAndSecondPins() < Pin.MAXIMUM_PIN_COUNT && rollingPin.size() == 2;
    }
}
