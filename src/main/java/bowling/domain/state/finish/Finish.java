package bowling.domain.state.finish;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.state.FinishStateBowlException;

public abstract class Finish implements State {

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State bowl(Pin pin) {
        throw new FinishStateBowlException();
    }

}
