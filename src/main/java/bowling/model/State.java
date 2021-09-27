package bowling.model;

import bowling.CannotBowlException;

public interface State {
    State bowl(int countOfPin) throws CannotBowlException;
}
