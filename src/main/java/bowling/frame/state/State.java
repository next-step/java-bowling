package bowling.frame.state;

import bowling.score.Pin;

public abstract class State {

    public abstract State bowl(Pin fellPins);

    public abstract boolean isFinish();

}
