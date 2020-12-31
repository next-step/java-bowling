package step2.domain.state;

import step2.domain.Score;

public abstract class Finished implements State {

    @Override
    public State bowl(int fallingPins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
    }

}
