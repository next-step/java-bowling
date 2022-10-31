package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Point;
import bowling.domain.state.Started;
import bowling.domain.state.State;

import java.util.Optional;

public abstract class Frame {
    State state;
    Point point;
    Frame before;

    protected Frame() {
        this.state = new Started();
    }

    protected Frame(Frame before) {
        this.before = before;
        this.state = new Started();
    }

    public abstract boolean isFinish();

    public abstract void bowl(Pin pin);

    public int getRemainPins() {
        return state.getRemainPins();
    }

    public State getState() {
        return state;
    }

    public abstract Integer calculatePoint();

    public abstract boolean canAddPoint();

    public abstract void addPoint(Pin pin);

    public abstract Optional<Pin> getBonus();

}
