package bowling.domain.state;

import bowling.domain.Pins;

public class Ready implements State{

    @Override
    public State nextPitch(Pins pins) {
        if(pins.isStrike()) {
            return Strike.of(pins);
        }
        return null;
    }

    @Override
    public String display() {
        return null;
    }
}
