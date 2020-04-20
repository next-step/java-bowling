package bowling.domain.state;

import bowling.domain.Score;

public class Finished implements State {
    @Override
    public State bowl(Score score) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String display() {
        return null;
    }
}
