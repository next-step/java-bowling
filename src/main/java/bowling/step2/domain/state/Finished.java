package bowling.step2.domain.state;

import bowling.step2.domain.exception.BowlException;

abstract public class Finished implements State {
    @Override
    public State bowl(final int fallenPins) {
        throw new BowlException();
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
    
    @Override
    public boolean isSpare() {
        return false;
    }
}
