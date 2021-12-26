package bowling.domain.progress;

import bowling.domain.Pin;
import bowling.domain.result.status.PinResultState;

public interface Opened {

    PinResultState pitch(Pin pin);
}
