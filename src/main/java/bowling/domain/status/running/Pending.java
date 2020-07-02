package bowling.domain.status.running;

import bowling.domain.frame.Pins;
import bowling.domain.status.Running;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Miss;
import bowling.domain.status.finished.Spare;

public class Pending extends Running {
    private static final int SPARE_SCORE = 10;

    int firstPin;

    public Pending() {
    }

    public Pending(int firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public Status bowl(int downPin) {
        Pins pins = Pins.down(firstPin + downPin);
        if (pins.getDownPin() == SPARE_SCORE) {
            return new Spare(firstPin, downPin);
        }
        return new Miss(firstPin, downPin);
    }

    @Override
    public String printResult() {
        return isGutter(firstPin);
    }

    @Override
    public boolean isClearAllPins() {
        return false;
    }

    @Override
    public boolean canRemovePendingStatue() {
        return false;
    }
}
