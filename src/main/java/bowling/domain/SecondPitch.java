package bowling.domain;

import bowling.domain.interfaces.State;

public class SecondPitch implements State {
    @Override
    public State bowl(Pins pins, int count) {
        pins.fall(count);
        if (pins.isAllDown()) {
            return Result.SPARE;
        }

        return Result.MISS;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
