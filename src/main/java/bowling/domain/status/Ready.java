package bowling.domain.status;

import bowling.domain.Pin;

public class Ready extends Status {

    @Override
    public Status bowl(Pin pin) {
        if (pin.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pin);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
