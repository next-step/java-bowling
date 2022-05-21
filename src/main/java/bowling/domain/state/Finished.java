package bowling.domain.state;

import bowling.domain.pin.Pin;

public abstract class Finished implements State {

    @Override
    public State bowl(Pin no) {
        throw new IllegalStateException("can't bowl more pins");
    }

    @Override
    public boolean finished() {
        return true;
    }
}
