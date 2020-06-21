package bowling.domain.state;

import bowling.domain.pin.PinCount;

public interface State {

    State bowl(PinCount hitCount);

    boolean isFinish();

    boolean isMiss();

    String getDesc();
}
