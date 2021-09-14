package step3.state;

import step3.exceptions.CanNotThrowBallException;

abstract class Finished implements State {

    @Override
    public State bowl(int fallenPins) {
        throw new CanNotThrowBallException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
