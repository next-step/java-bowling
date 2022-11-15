package bowling.domain.state;

import bowling.domain.frame.Frame;

public class Spare implements HitState {

    private static final int NUMBER_OF_BONUS = 1;

    protected Spare() {
    }

    @Override
    public boolean identify(int hit, Integer previousHit) {
        return previousHit != null
                && previousHit != Frame.MAX_NUMBER_OF_BOWLING_PINS
                && hit + previousHit == Frame.MAX_NUMBER_OF_BOWLING_PINS;
    }

    @Override
    public int getNumberOfBonus() {
        return NUMBER_OF_BONUS;
    }

}
