package bowling.domain.state;

import bowling.domain.pin.DownedPins;

public abstract class EndState extends State {
    @Override
    protected State nextState(DownedPins downedPins) {
        throw new IllegalStateException("EndState can't down pins");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
