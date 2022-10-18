package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.state.Started;
import bowling.domain.state.State;

public abstract class Frame {
    State state;

    protected Frame() {
        this.state = new Started();
    }

    public abstract boolean isFinish();
    public abstract void bowl(Pin pin);

    public int getRemainPins(){
        return state.getRemainPins();
    }

    public State getState() {
        return state;
    }


}
