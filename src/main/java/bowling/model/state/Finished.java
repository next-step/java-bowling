package bowling.model.state;

import bowling.model.CannotBowlException;

abstract class Finished implements State {
    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        throw new CannotBowlException();
    }

    @Override
    public boolean isFinish(){
        return true;
    }
}
