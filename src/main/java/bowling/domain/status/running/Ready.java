package bowling.domain.status.running;

import bowling.domain.frame.Pins;
import bowling.domain.status.Running;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Strike;

public class Ready extends Running {

    public static final int STRIKE_SCORE = 10;

    public Ready() {
    }

    @Override
    public Status bowl(int downPin) {
        Pins pins = Pins.down(downPin);
        if (pins.getDownPin() == STRIKE_SCORE) {
            return new Strike();
        }
        return new Pending(downPin);
    }

    @Override
    public String printResult() {
        return "";
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
