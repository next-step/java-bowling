package bowling.domain.state;

public abstract class Running implements FrameState {
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean isStrike() {
        return false;
    }
}
