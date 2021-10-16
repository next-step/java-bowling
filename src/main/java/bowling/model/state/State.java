package bowling.model.state;

import bowling.CannotBowlException;

public interface State {
    State bowl(int countOfPin) throws CannotBowlException;

    boolean isFinish();
}
