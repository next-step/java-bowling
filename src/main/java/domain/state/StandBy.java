package domain.state;

import domain.Pins;

public class StandBy implements State {
    private static final String STAND_BY_SYMBOL = " ";

    @Override
    public State update(Pins fallenPins) {
        if (fallenPins.isStrike()) {
            return new Strike();
        }
        return new Hit(fallenPins);
    }

    @Override
    public boolean isClosed() {
        return Boolean.FALSE;
    }

    @Override
    public String printState() {
        return STAND_BY_SYMBOL;
    }
}
