package bowling.model.state;

import bowling.model.Pin;

public abstract class Running implements State{
    @Override
    public abstract State bowl(Pin pin);
}
