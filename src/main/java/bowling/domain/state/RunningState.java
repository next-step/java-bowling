package bowling.domain.state;

public abstract class RunningState implements ThrowingState {
    @Override
    public boolean isEnd() {
        return false;
    }
}
