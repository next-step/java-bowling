package bowling.domain.state;

import bowling.domain.frame.Frame;

public class Gutter implements HitState {

    protected Gutter() {
    }

    @Override
    public boolean identify(int hit, Integer previousHit) {
        return hit == Frame.MIN_NUMBER_OF_BOWLING_PINS;
    }

}
