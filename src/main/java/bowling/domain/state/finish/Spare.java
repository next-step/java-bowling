package bowling.domain.state.finish;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.state.FinishStateBowlException;

public class Spare implements State {

    private final Pin first;
    private final Pin second;

    public Spare(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Score createScore() {
        return null;
    }

    @Override
    public State bowl(Pin pin) {
        throw new FinishStateBowlException();
    }

}
