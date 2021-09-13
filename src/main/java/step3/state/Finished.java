package step3.state;

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

    @Override
    public boolean isFinish() {
        return true;
    }
}
