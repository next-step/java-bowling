package bowling.model.state;

import bowling.CannotBowlException;

public class ThirdSpare extends Finished {
    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        return null;
    }
}
