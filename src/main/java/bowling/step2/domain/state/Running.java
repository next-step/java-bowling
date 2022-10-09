package bowling.step2.domain.state;

import bowling.step2.domain.exception.DisplayException;

abstract public class Running implements State {
    @Override
    public boolean isFinished() {
        return false;
    }
    
    @Override
    public boolean isSpare() {
        return false;
    }
}
