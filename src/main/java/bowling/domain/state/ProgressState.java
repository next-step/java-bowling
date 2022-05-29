package bowling.domain.state;

public abstract class ProgressState implements FrameState {
    @Override
    public boolean isEnd() {
        return false;
    }
}