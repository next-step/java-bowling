package bowling.model.state;

import bowling.model.CannotCalculateException;
import bowling.model.Score;

abstract class Running implements State {
    @Override
    public boolean isFinish(){
        return false;
    }

    @Override
    public Score getScore() {
        throw new CannotCalculateException();
    }
}
