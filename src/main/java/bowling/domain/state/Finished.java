package bowling.domain.state;

import static java.lang.Boolean.TRUE;

import bowling.domain.Pins;
import bowling.exception.NotBowlingStateException;

public abstract class Finished implements State {

    @Override
    public State bowl(Pins pins) {
        throw new NotBowlingStateException();
    }

    @Override
    public boolean isFinished() {
        return TRUE;
    }

}
