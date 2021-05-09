package bowling.domain.frame;

import bowling.domain.state.Pins;

public abstract class Frame {

    public abstract Frame bowl(Pins fallPins);

    public abstract boolean isFinish();

}
