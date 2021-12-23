package bowling.domain.state.ready;

import bowling.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.state.UpdateAbleState;
import bowling.domain.state.State;
import bowling.domain.state.progress.FirstProgress;

public class Ready implements State, UpdateAbleState {

    @Override
    public State getUpdateState(Frame frame, Pin pin) {
        return new FirstProgress(frame, pin);
    }
}
