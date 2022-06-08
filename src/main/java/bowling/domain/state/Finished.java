package bowling.domain.state;

import bowling.exception.NotSupportMethodException;

abstract class Finished implements State {

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public State bowl(int hit) {
        throw new NotSupportMethodException();
    }
}
