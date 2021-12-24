package bowling.domain.progress;

import bowling.domain.Pin;
import bowling.domain.state.end.ResultState;

public interface Opened {

    ResultState pitch(Pin pin);
}
