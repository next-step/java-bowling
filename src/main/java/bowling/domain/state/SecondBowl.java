package bowling.domain.state;

import bowling.exception.EndedFrameException;

public class SecondBowl implements EndedState {
    private RunningState previousState;

    public SecondBowl(RunningState previousState) {
        this.previousState = previousState;
    }

    @Override
    public ThrowingState bowl() {
        throw new EndedFrameException();
    }

    @Override
    public String symbol() {
        return previousState.symbol() + "│" + previousState.frame().second();
    }
}
