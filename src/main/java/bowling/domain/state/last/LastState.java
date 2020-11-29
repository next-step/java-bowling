package bowling.domain.state.last;

import bowling.domain.frame.InvalidFrameRecordActionException;
import bowling.domain.state.State;

public interface LastState extends State {
    default State record(int score) {
        throw new InvalidFrameRecordActionException();
    }

    default boolean isFinished() {
        return true;
    }
}
