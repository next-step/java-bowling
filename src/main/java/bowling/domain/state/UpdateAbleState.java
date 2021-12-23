package bowling.domain.state;

import bowling.Pin;
import bowling.domain.frame.Frame;

public interface UpdateAbleState {

    State getUpdateState(Frame frame, Pin pin);
}
