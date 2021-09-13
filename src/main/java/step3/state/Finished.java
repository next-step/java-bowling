package step3.state;

import step3.Score;
import step3.exceptions.CanNotThrowBallException;
import step3.state.State;

abstract class Finished implements State {
    private Score score;

    @Override
    public State bowl(int fallenPins) {
        throw new CanNotThrowBallException();
    }

    @Override
    public Score score() {
        return score;
    }
}
