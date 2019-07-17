package domain.state;

import domain.Pins;

public class StandBy implements State {

    @Override
    public State update(Pins fallenPins) {
        if (fallenPins.isStrike()) {
            return new Strike();
        }
        return new Hit(fallenPins);
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
