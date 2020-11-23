package bowling.frame.state;

import bowling.score.Pin;

public abstract class Done extends State {

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public State bowl(Pin fellPins) {
        throw new UnsupportedOperationException();
    }
}
