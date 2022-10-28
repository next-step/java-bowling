package bowling.domain.state;

import bowling.domain.pin.FallenPin;

public class Ready extends Running {

    @Override
    public State bowl(FallenPin fallenPin) {
        if (fallenPin.isMax()) {
            return new Strike();
        }

        return new FirstBowling(fallenPin);
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public int tries() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Ready;
    }

    @Override
    public int hashCode() {
        return Ready.class.hashCode();
    }
}
