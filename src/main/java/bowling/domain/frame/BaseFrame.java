package bowling.domain.frame;

import bowling.domain.engine.Frame;
import bowling.domain.engine.State;
import bowling.domain.pin.Pins;
import bowling.domain.state.run.Ready;

public abstract class BaseFrame implements Frame {

    protected State state;
    protected Pins pins;
    protected int round;

    BaseFrame(final int round) {
        this.round = round;
        this.state = new Ready();
        this.pins = Pins.init();
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public int round() {
        return round;
    }

    @Override
    public boolean isNextFrame() {
        return true;
    }

}
