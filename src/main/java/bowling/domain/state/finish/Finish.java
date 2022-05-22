package bowling.domain.state.finish;

import bowling.domain.Pins;
import bowling.domain.state.State;
import bowling.exception.ImpossiblePitchException;

public abstract class Finish implements State {

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public State pitch(Pins pins) {
        throw new ImpossiblePitchException(Finish.class.getName());
    }

}
