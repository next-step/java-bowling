package bowling.domain;

import bowling.domain.interfaces.State;

public class FirstPitch implements State {

    @Override
    public State bowl(Pins pins, int count) {
        pins.fall(count);
        if (pins.isAllDown()) {
            return Result.STRIKE;
        }

        return new SecondPitch();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
