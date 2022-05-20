package bowling.domain.state;

import bowling.exception.EndedFrameException;

public class Spare implements EndedState {
    private RunningState previousState;

    public Spare(RunningState previousState) {
        this.previousState = previousState;
    }

    @Override
    public ThrowingState bowl() {
        throw new EndedFrameException();
    }

    @Override
    public String symbol() {
        return previousState.symbol() + "│/";
    }
}
