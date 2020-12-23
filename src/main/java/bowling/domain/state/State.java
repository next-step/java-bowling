package bowling.domain.state;

import bowling.domain.Pitch;

public abstract class State {

    private Pitch pitch;

    public State bowl(Pitch pitch) {
        return null;
    }

    public boolean isFinish() {
        return false;
    }

}
