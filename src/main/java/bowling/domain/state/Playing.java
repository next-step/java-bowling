package bowling.domain.state;

import bowling.domain.Score;

public abstract class Playing implements State {
    @Override
    public boolean isFinished() {
        return false;
    }
}
