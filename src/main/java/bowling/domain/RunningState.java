package bowling.domain;

public abstract class RunningState implements State {
    @Override
    public boolean isEnd() {
        return false;
    }
}
