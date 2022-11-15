package bowling.domain.state;

import bowling.domain.frame.Frame;

public class Miss implements HitState {

    protected Miss() {
    }

    @Override
    public boolean identify(int hit, Integer previousHit) {
        return previousHit != null && hit + previousHit < Frame.MAX_NUMBER_OF_BOWLING_PINS;
    }

}
