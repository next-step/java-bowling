package bowling.domain.frame;

import bowling.domain.Point;
import bowling.domain.state.State;

import java.util.List;

public abstract class AbstractFrame implements Frame {
    protected final int order;
    protected State state;

    public AbstractFrame(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public List<Point> getPoints() {
        return state.getPoints();
    }

}
