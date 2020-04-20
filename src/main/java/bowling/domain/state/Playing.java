package bowling.domain.state;

import bowling.domain.Score;

public class Playing implements State {
    @Override
    public State bowl(Score score) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return null;
    }
}
