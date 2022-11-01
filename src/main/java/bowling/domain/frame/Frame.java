package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Point;
import bowling.domain.state.Started;
import bowling.domain.state.State;

import java.util.Optional;

public abstract class Frame {
    State state;
    Point point;

    protected Frame(Point before) {
        this.point = new Point(before);
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

    public Integer calculatePoint(){

        if( point == null || canAddPoint() ){
            return null;
        }

        return point.point();
    }

    public abstract boolean canAddPoint();

    public abstract void addPoint(Pin pin);

    public abstract Optional<Pin> getBonus();

}
