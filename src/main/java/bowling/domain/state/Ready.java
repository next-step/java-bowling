package bowling.domain.state;

import static java.lang.Boolean.FALSE;

import bowling.domain.Pins;

public class Ready implements State {

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

    @Override
    public boolean isFinished() {
        return FALSE;
    }
}
