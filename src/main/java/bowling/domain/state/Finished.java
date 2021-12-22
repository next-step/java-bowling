package bowling.domain.state;

import bowling.domain.value.Pins;

public abstract class Finished implements State{

    @Override
    public State bowl(Pins pins) {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
