package bowling.domain.state;

import bowling.domain.Pins;

public class Ready extends Running {

    private static final String MARKING = "";

    @Override
    public State bowl(Pins pins) {
        if (pins.isStrike()) {
            return new Strike(pins);
        }
        return new FirstBowl(pins);
    }

    @Override
    public String mark() {
        return MARKING;
    }

}
