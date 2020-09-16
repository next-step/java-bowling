package bowling.domain.state;

import bowling.domain.State;

abstract class Finished implements State {

    @Override
    public State roll(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
