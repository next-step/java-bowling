package bowling.domain.progress;

import bowling.domain.Pin;
import bowling.domain.state.end.EndState;

public interface Opened {

    EndState pitch(Pin pin);
}
