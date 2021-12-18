package bowling.model.state;

import bowling.model.Pins;

public abstract class Finished implements State {
    @Override
    public State bowl(Pins knockedDownPin) {
        return this;
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
