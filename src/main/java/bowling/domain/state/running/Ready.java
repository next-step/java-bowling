package bowling.domain.state.running;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;

public class Ready implements State {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score createScore() {
        return null;
    }

    @Override
    public State bowl(Pin pin) {
        return null;
    }

}
