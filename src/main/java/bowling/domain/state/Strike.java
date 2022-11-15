package bowling.domain.state;

import bowling.domain.frame.Frame;

public class Strike implements HitState {

    private static final int NUMBER_OF_BONUS = 2;

    protected Strike() {
    }

    @Override
    public boolean identify(int hit, Integer previousHit) {
        return (previousHit == null || previousHit != 0) && hit == Frame.MAX_NUMBER_OF_BOWLING_PINS;
    }

    @Override
    public int getNumberOfBonus() {
        return NUMBER_OF_BONUS;
    }

}
