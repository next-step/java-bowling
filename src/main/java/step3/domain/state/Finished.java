package step3.domain.state;

import step3.domain.Score;
import step3.exceptions.CanNotThrowBallException;

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
