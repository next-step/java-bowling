package bowling.domain.state;

public abstract class Finished implements State {
    @Override
    public boolean isFinished() {
        return true;
    }
}
