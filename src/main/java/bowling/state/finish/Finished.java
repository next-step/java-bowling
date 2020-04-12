package bowling.state.finish;

import bowling.state.State;

public abstract class Finished implements State {

    @Override
    public State bowl(int felledPins) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
