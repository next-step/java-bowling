package step3;

import step3.exceptions.CanNotThrowBallException;

public class Finished implements State {
    @Override
    public State bowl(int fallenPins) {
        throw new CanNotThrowBallException();

    }
}
