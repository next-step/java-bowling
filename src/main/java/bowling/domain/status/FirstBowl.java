package bowling.domain.status;

import bowling.domain.Pin;

public class FirstBowl extends Status {

    public FirstBowl(Pin pin) {
        this.first = pin;
    }

    @Override
    public Status bowl(Pin second) {
        if (Pin.sumBowls(first, second).isAllPinsDown()) {
            return new Spare(first, second);
        }
        return new Miss(first, second);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
