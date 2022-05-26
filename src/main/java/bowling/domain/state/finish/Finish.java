package bowling.domain.state.finish;

import bowling.domain.Pins;
import bowling.domain.state.State;
import bowling.exception.ImpossiblePitchException;

public interface Finish extends State {

    default boolean isFrameEnd() {
        return true;
    }

    default State pitch(Pins pins) {
        throw new ImpossiblePitchException(State.class.getName());
    }

}
