package step3.state;

import step3.exceptions.CanNotThrowBallException;
import step3.state.State;

public class Finished implements State {
    @Override
    public State bowl(int fallenPins) {
        throw new CanNotThrowBallException();

    }
}
