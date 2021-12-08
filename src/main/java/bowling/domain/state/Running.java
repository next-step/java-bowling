package bowling.domain.state;

import bowling.domain.Pin;

public abstract class Running implements State {

    abstract State bowl(Pin pin);

    @Override
    public boolean isFinished() {
        return false;
    }
}
