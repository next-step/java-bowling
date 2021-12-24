package bowling.domain.progress;

import bowling.domain.Pin;
import bowling.domain.result.ResultState;

public interface Opened {

    ResultState pitch(Pin pin);
}
