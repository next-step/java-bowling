package bowling.domain.state;

import bowling.domain.Pitch;

public abstract class State {

    private Pitch pitch;

    State bowl(Pitch pitch) {
        return null;
    }

    boolean isFinish() {
        return false;
    }

    int getScore() {
        return 0;
    }

    int calculateAdditionalState(int score) {
        return 0;
    }

}
