package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Point;
import bowling.domain.state.Started;
import bowling.domain.state.State;

import java.util.List;

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

    public Integer calculatePoint() {

        if (point == null || isNotFinishedCalculate()) {
            return null;
        }

        return point.calculate();
    }

    private boolean isNotFinishedCalculate() {
        return canAddPoint() || !isFinish();
    }

    public abstract boolean canAddPoint();

    public abstract void addPoint(Pin pin);

    public abstract List<Pin> getBonus();

}
