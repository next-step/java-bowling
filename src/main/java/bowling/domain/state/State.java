package bowling.domain.state;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;

import java.util.List;

public interface State {

    State bowl(PinCount hitCount);

    boolean isFinish();

    boolean isMiss();

    boolean isCleanState();

    Pins getFirstPins();

    Pins getSecondPins();

    List<State> getState();
}
