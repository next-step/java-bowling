package bowling.domain.state;

public abstract class Running implements FrameState {
    @Override
    public boolean isFinished() {
        return false;
    }
}
